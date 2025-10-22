package co.com.juandaza.r2dbc.gateways;

import co.com.juandaza.model.user.User;
import co.com.juandaza.model.user.gateways.UsersGateway;
import co.com.juandaza.r2dbc.entities.UsersEntity;
import co.com.juandaza.r2dbc.mappers.users.UsersMappers;
import co.com.juandaza.r2dbc.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class UsersGatewayAdapter implements UsersGateway {
    private final UsersRepository usersRepository;
    private final UsersMappers usersMappers;

    @Override
    public Flux<User> getUserAll() {
        return usersRepository.findAllUsers()
                .doOnNext(user -> log.info("Usuario desde BD: {}", user))
                .map(usersMappers::toModel);
    }



    @Override
    public Mono<User> saveUser(User users) {
        log.info("Transformando User a UsersEntity para guardar en BD");
        UsersEntity usersEntity = usersMappers.toEntity(users);
        log.info("UsersEntity mapeado: {}", usersEntity);
        return usersRepository.saveUser(usersEntity)
                .map(usersMappers::toModel)
                .doOnNext(user -> log.info("Usuario nuevo: {}", user));
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> existById(String idUsers) {
        return usersRepository.existsById(idUsers);
    }
}
