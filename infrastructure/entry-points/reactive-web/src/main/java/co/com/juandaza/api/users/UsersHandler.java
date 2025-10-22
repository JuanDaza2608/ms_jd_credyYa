package co.com.juandaza.api.users;

import co.com.juandaza.model.user.User;
import co.com.juandaza.usecase.users.UsersUseCase;
import co.com.juandaza.usecase.util.BusinessException;
import co.com.juandaza.usecase.util.messages.MessageError;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class UsersHandler {
    private final UsersUseCase usersUseCase;

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return usersUseCase.getAllUsers()
                .doOnNext(user -> log.info("Usuario obtenido: {}", user))
                .collectList()
                .flatMap(users -> {
                    log.info("Usuarios obtenidos: {}", users.size());
                    return ServerResponse.ok().bodyValue(users);
                })
                .doOnError(error -> log.error("Error al obtener usuarios: ", error))
                .onErrorResume(error ->
                        ServerResponse.status(500).bodyValue("Error interno"));
    }

    public Mono<ServerResponse> saveUsers(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> {
                    if (user.getIdUsuario() == null) {
                        return usersUseCase.saveUser(user)
                                .flatMap(saved -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(saved));
                    } else {
                        log.warn("Se recibió un idUsuario en la petición: {}", user.getIdUsuario());
                        return ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new MessageError(
                                        HttpStatus.BAD_REQUEST.value(),
                                        "El idUsuario debe ser nulo, se genera automáticamente",
                                        request.path()
                                ));
                    }
                })
                .onErrorResume(BusinessException.class, error -> {
                    log.warn("Error de negocio al guardar usuario: {}", error.getMessage());
                    return ServerResponse.status(HttpStatus.BAD_REQUEST)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new MessageError(
                                    HttpStatus.BAD_REQUEST.value(),
                                    error.getMessage(),
                                    request.path()
                            ));
                })
                .onErrorResume(e -> {
                    log.error("Error inesperado al guardar usuario", e);
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new MessageError(
                                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                    "Error interno del servidor",
                                    request.path()
                            ));
                });
    }


}
