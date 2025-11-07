package co.com.juandaza.api.approved;


import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.usecase.approved.ApprovedUseCase;
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
public class ApprovedHandler {

    private final ApprovedUseCase approvedUseCase;


    public Mono getApproved(ServerRequest request) {
        log.info("#############################");
        log.info("Entrando a aprovar la solicitud");

        return request.bodyToMono(Solicitud.class)
                .flatMap(approvedUseCase::validateState)
                .flatMap(approvedUseCase::approvedSolicitud)
                .flatMap(solicitud -> ServerResponse.ok().bodyValue(solicitud))
                .onErrorResume(BusinessException.class, ex ->
                        ServerResponse.badRequest().bodyValue(Map.of(
                                "timestamp", LocalDateTime.now().toString(),
                                "codigo", "BUS-001",
                                "mensaje", ex.getMessage()
                        ))
                )
                .onErrorResume(Exception.class, ex ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(Map.of(
                                "codigo", "GEN-500",
                                "mensaje", "Error interno del servidor",
                                "detalle", ex.getMessage()
                        ))
                )
                ;
    }

}
