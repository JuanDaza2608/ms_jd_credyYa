package co.com.juandaza.r2dbc.solicitud;

import co.com.juandaza.r2dbc.entities.SolicitudEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SolicitudRepository extends ReactiveCrudRepository<SolicitudEntity,String> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ms_jd_credy_ya.Usuarios u " +
            "WHERE u.email = :u AND u.documento_identidad = :d")
    Mono<Boolean> findUserByEmailId(@Param("u") String email, @Param("d") String numDoc);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ms_jd_credy_ya.tipo_prestamo u " +
            "WHERE u.id_tipo_prestamo =:u")
    Mono<Boolean> findLoandType(@Param("u") String loandType);

    @Query("INSERT INTO ms_jd_credy_ya.Solicitud " +
            "(id_solicitud, monto, plazo, email, id_estado, iid_tipo_prestamo) " +
            "VALUES (:id, :monto, :plazo, :email, :estado, :tipoPrestamo)")
    Mono<Void> insertSolicitud(
            @Param("id") String id,
            @Param("monto") String monto,
            @Param("plazo") String plazo,
            @Param("email") String email,
            @Param("estado") String estado,
            @Param("tipoPrestamo") String tipoPrestamo);


    Mono<Boolean> existsById(String idSolicituds);
}
