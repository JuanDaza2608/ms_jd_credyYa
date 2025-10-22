package co.com.juandaza.usecase.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.solicitudModel.gateways.SolicitudGateway;
import co.com.juandaza.model.solicitudModel.gateways.TipoPrestamo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SolicitudUseCase {
    private final SolicitudGateway solicitudGateway;


    public Mono<Solicitud> saveSolicitud(Solicitud solicitud) {
        return solicitudGateway.saveSolicitud(solicitud.getPrestamo())
                .flatMap(tipoPrestamo -> {
                    // Validación de rango
                    if (solicitud.getMonto().doubleValue() < tipoPrestamo.getMonMin().doubleValue()
                            || solicitud.getMonto().doubleValue() > tipoPrestamo.getMonMax().doubleValue()) {
                        return Mono.error(new IllegalArgumentException(
                                String.format("El monto %.2f está fuera del rango permitido [%.2f - %.2f]",
                                        solicitud.getMonto().doubleValue(),
                                        tipoPrestamo.getMonMin().doubleValue(),
                                        tipoPrestamo.getMonMax().doubleValue())
                        ));
                    }

                    // ✅ Aquí continuarías con la lógica de guardar la solicitud
                    return solicitudGateway.saveSolicitud(solicitud);
                });
    }
}
