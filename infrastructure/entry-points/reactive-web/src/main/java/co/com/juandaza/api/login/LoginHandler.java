package co.com.juandaza.api.login;

import co.com.juandaza.model.login.Login;
import co.com.juandaza.usecase.login.LoginUseCase;
import co.com.juandaza.usecase.util.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor

public class LoginHandler {

    private final LoginUseCase loginUseCase;
    public Mono getUserLogin(ServerRequest serverRequest) {
        log.info("#############################");
        log.info("Entrando a login de usuario");

        // del body del json de entrada se coge un objeto tipo SOLICITUD
        return serverRequest.bodyToMono(Login.class)
                .flatMap(loginUseCase::validateUserLogin)
                .flatMap(login -> ServerResponse.ok().bodyValue("Usuario encontrado"))


                .onErrorResume(BusinessException.class, ex ->
                        ServerResponse.badRequest().bodyValue(Map.of(
                                "timestamp", LocalDateTime.now().toString(),
                                "codigo", ex.getCode(),
                                "mensaje", ex.getMessage()
                        ))
                )
                .onErrorResume(Exception.class, ex ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(Map.of(
                                "codigo", "GEN-500",
                                "mensaje", "Error interno del servidor",
                                "detalle", ex.getMessage()
                        ))
                );

    }
}
