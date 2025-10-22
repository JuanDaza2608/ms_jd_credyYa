package co.com.juandaza.r2dbc.users;

import co.com.juandaza.r2dbc.entities.UsersEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<UsersEntity, String> {
    @Query("select * from ms_jd_credy_ya.Usuarios")
    Flux<UsersEntity> findAllUsers();

    @Query("INSERT INTO ms_jd_credy_ya.Usuarios (id_usuario, nombre, apellido, email, documento_identidad, telefono, id_rol, salario_base) " +
            "VALUES (:#{#u.idUsuario}, :#{#u.nombre}, :#{#u.apellido}, :#{#u.email}, :#{#u.nroIdentificacion}, :#{#u.telefono}, :#{#u.idRol}, :#{#u.salarioBase}) " +
            "RETURNING *")
    Mono<UsersEntity> saveUser(@Param("u") UsersEntity usersEntity);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsById(String id);;


}
