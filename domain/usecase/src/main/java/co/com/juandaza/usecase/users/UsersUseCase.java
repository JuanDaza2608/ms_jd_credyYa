package co.com.juandaza.usecase.users;

import co.com.juandaza.model.user.User;
import co.com.juandaza.model.user.gateways.UsersGateway;
import co.com.juandaza.usecase.util.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class UsersUseCase {
    private final UsersGateway usersGateway;

    public Flux<User> getAllUsers () {
        return usersGateway.getUserAll();
    }

    public Mono<User> saveUser(User user) {
        return usersGateway.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                       return Mono.error(new BusinessException("USR-001","El correo ya está registrado"));
                    }else {
                        return generateUniqueId()
                                .map(randomId -> {
                                    user.setIdUsuario(randomId);
                                    return user;
                                })
                                .flatMap(usersGateway::saveUser);
                    }
                });
    }

    public Mono<String> generateUniqueId() {
        String randomIdUser = UUID.randomUUID().toString();
        return usersGateway.existById(randomIdUser)
                .flatMap(exists -> {
                    if (exists) {
                        // Si el ID ya existe, generar otro (recursivo)
                        return generateUniqueId();
                    } else {
                        return Mono.just(randomIdUser);
                    }
                });
    }
}
