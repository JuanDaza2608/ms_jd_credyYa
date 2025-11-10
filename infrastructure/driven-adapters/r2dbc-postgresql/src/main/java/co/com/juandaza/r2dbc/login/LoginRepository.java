package co.com.juandaza.r2dbc.login;

import co.com.juandaza.r2dbc.entities.LoginEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LoginRepository extends ReactiveCrudRepository<LoginEntity, String> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM ms_jd_credy_ya.login u WHERE u.userlogin = :u AND u.passwordlogin = :d")
    Mono<Boolean> validateUserLogin(@Param("u") String userLogin, @Param("d") String passwordLogin);
}
