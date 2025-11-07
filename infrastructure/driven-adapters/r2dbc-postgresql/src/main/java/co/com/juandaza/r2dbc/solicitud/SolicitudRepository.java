package co.com.juandaza.r2dbc.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.user.User;
import co.com.juandaza.r2dbc.entities.SolicitudEntity;
import co.com.juandaza.r2dbc.entities.UsersEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SolicitudRepository extends ReactiveCrudRepository<SolicitudEntity,String> {

    @Query("SELECT * " +
            "FROM ms_jd_credy_ya.Usuarios u " +
            "WHERE u.email = :u AND u.documento_identidad = :d")
    Mono<User> findUserByEmailId(@Param("u") String email, @Param("d") String numDoc);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ms_jd_credy_ya.tipo_prestamo u " +
            "WHERE u.id_tipo_prestamo =:u")
    Mono<Boolean> findLoandType(@Param("u") String loandType);

    @Query("INSERT INTO ms_jd_credy_ya.Solicitud " +
            "(id_solicitud, monto, plazo, email, id_estado, iid_tipo_prestamo) " +
            "VALUES (:id, :monto, :plazo, :email, 'PENDIENTE', :tipoPrestamo)")
    Mono<Void> insertSolicitud(
            @Param("id") String id,
            @Param("monto") String monto,
            @Param("plazo") String plazo,
            @Param("email") String email,
            @Param("tipoPrestamo") String tipoPrestamo);


    Mono<Boolean> existsById(String idSolicitud);


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ms_jd_credy_ya.solicitud u " +
            "WHERE u.id_solicitud = :u and (u.id_estado  <> 'APROVADO' or u.id_estado = 'RECHAZADO')")
    Mono<Boolean> validateState(@Param("u") String idSolicitud);



    @Query("UPDATE ms_jd_credy_ya.Solicitud SET id_estado = :d WHERE id_solicitud = :u")
    Mono<Void> updateState(@Param("u") String idSolicitud, @Param("d") String idState);

}
