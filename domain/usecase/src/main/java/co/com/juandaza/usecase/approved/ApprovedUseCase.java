package co.com.juandaza.usecase.approved;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.solicitudModel.gateways.SolicitudGateway;
import co.com.juandaza.usecase.util.BusinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ApprovedUseCase {

    private final SolicitudGateway solicitudGateway;

    public Mono<Solicitud> validateState(Solicitud solicitud){
        System.out.println("Entrado a validar el estado de la solicitud:   " + solicitud.getIdSolicitud());
        return solicitudGateway.validateState(solicitud.getIdSolicitud())
                .flatMap(estado -> {
                    if (!estado) {
                        return Mono.error(new BusinessException("APR-001","Estado de la solicitud invalido"));
                    }
                    return Mono.just(solicitud);
                });
    }

    public Mono<Void> approvedSolicitud(Solicitud solicitud){
        return solicitudGateway.approvedSolicitud(solicitud.getIdSolicitud(), solicitud.getIdEstado());
    }
}
