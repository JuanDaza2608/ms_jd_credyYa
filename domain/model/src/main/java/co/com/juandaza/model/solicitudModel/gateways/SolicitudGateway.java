package co.com.juandaza.model.solicitudModel.gateways;

import co.com.juandaza.model.solicitudModel.Solicitud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudGateway {

    Mono<TipoPrestamo> getTipoPrestamo(String tipPrestamo);

    Mono<Solicitud> saveSolicitud(Solicitud solicitud);

}
