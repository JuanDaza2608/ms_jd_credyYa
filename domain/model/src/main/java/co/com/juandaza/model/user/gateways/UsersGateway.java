package co.com.juandaza.model.user.gateways;

import co.com.juandaza.model.user.User;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UsersGateway {
    Flux<User> getUserAll();

    Mono<User> saveUser(User users);

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existById(String idUsers);
}
