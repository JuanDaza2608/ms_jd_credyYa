package co.com.juandaza.model.solicitudModel.gateways;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.user.User;
import reactor.core.publisher.Mono;

public interface SolicitudGateway {

    Mono<Solicitud> saveSolicitud(Solicitud solicitud);

    Mono<User> validateUser(String email, String numDoc);

    Mono<Boolean> validateLoanType(String loandType);

    Mono<Boolean> existById(String idSolicitud);

    Mono<Void> approvedSolicitud(String idSolicitud, String idEstado);

    Mono<Boolean> validateState(String idState);

}
