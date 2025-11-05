package co.com.juandaza.usecase.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.solicitudModel.gateways.SolicitudGateway;
import co.com.juandaza.model.solicitudModel.gateways.TipoPrestamo;
import co.com.juandaza.usecase.util.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RequiredArgsConstructor
public class SolicitudUseCase {
    private final SolicitudGateway solicitudGateway;

    public Mono<Solicitud> validateUser(Solicitud solicitud){
        System.out.println("Entra a validad usuario con los datos: Correo --> " + solicitud.getEmail() + " //// Numero Identificacion --> " + solicitud.getNroIdentificacion());
        return solicitudGateway.validateUser(solicitud.getEmail(), solicitud.getNroIdentificacion())
                .flatMap(existe -> {
                    if(!existe) {
                        return Mono.error(new BusinessException("Usuario no encontrado"));
                    }
                    return Mono.just(solicitud);
                });
    }

    public Mono<Solicitud> validateLoanType(Solicitud solicitud){
        System.out.println("Validando tipo de prestamo:  " + solicitud.getPrestamoTipo());
        return solicitudGateway.validateLoanType(solicitud.getPrestamoTipo())
                .flatMap(existe -> {
                    if(!existe){
                        return Mono.error(new BusinessException("Tipo de Solicitud invalido"));
                    }
                    return Mono.just(solicitud);
                });
    }

    public Mono<Solicitud> validateMon(Solicitud solicitud){
        System.out.println("Validando los Montos de la solicitud");
        return null;
    }

    public Mono<Solicitud> saveSolicitud(Solicitud solicitud) {
        System.out.println("Los Datos son correctos, se procede a crear solicitud");
        return null;
//        return solicitudGateway.saveSolicitud(solicitud)
//                .flatMap(tipoPrestamo -> {
//                    // Validación de rango
//                    if (solicitud.getMonto().doubleValue() <
//                            || solicitud.getMonto().doubleValue() > tipoPrestamo.getMonMax().doubleValue()) {
//                        return Mono.error(new IllegalArgumentException(
//                                String.format("El monto %.2f está fuera del rango permitido [%.2f - %.2f]",
//                                        solicitud.getMonto().doubleValue(),
//                                        tipoPrestamo.getMonMin().doubleValue(),
//                                        tipoPrestamo.getMonMax().doubleValue())
//                        ));
//                    }
//
//                    // ✅ Aquí continuarías con la lógica de guardar la solicitud
//                    return solicitudGateway.saveSolicitud(solicitud);
//                });

    }
    public Mono<String> generateUniqueIdSolicitud() {
        return null;
    }
//        String randomIdSolicitud = UUID.randomUUID().toString();
//        return solicitudGateway.existById(randomIdUser)
//                .flatMap(exists -> {
//                    if (exists) {
//                        // Si el ID ya existe, generar otro (recursivo)
//                        return generateUniqueId();
//                    } else {
//                        return Mono.just(randomIdUser);
//                    }
//                });
//    }
}

