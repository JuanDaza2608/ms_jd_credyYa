package co.com.juandaza.api.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.usecase.solicitud.SolicitudUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class SolicitudHandler {
    private final SolicitudUseCase solicitudUseCase;


    public Mono<ServerResponse> saveSolicitud(ServerRequest serverRequest) {
        log.info("Entrando a ingresar solicitud");
        return serverRequest.bodyToMono(Solicitud.class)
                .flatMap(solicitud ->
                        solicitudUseCase.saveSolicitud(solicitud)
                                .flatMap(tipoPrestamo ->
                                        ServerResponse.ok().bodyValue(tipoPrestamo)
                                )
                )
                .doOnError(error -> log.error("Error al generar solicitud: ", error))
                .onErrorResume(error ->
                        ServerResponse.status(500).bodyValue("Error interno"));
    }
}
