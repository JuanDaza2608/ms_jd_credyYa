package co.com.juandaza.usecase.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.solicitudModel.gateways.SolicitudGateway;
import co.com.juandaza.model.solicitudModel.gateways.TipoPrestamo;
import co.com.juandaza.model.user.User;
import co.com.juandaza.usecase.util.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@RequiredArgsConstructor
public class SolicitudUseCase {
    private final SolicitudGateway solicitudGateway;
    private Number SalaryBase;

    public Mono<Solicitud> validateUser(Solicitud solicitud){
        System.out.println("Entra a validad usuario con los datos: Correo --> " + solicitud.getEmail() + " //// Numero Identificacion --> " + solicitud.getNroIdentificacion());
        return solicitudGateway.validateUser(solicitud.getEmail(), solicitud.getNroIdentificacion())
                .map(user -> {
                    SalaryBase = user.getSalarioBase();
                    return (solicitud);
                })
                .switchIfEmpty(Mono.error(new BusinessException("Usuario no encontrado")))
                .thenReturn(solicitud);
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
        System.out.println("Validando los Montos de la solicitud con SALARIO --> " + solicitud.getMonto().doubleValue() + "   y  SalarioBase --> " + SalaryBase);
        if (solicitud.getMonto().doubleValue() > SalaryBase.doubleValue()){
            return Mono.error(new BusinessException("El monto de solicitud es mayor al salario del empleado"));
        }
        return Mono.just(solicitud);
    }

    public Mono<Solicitud> saveSolicitud(Solicitud solicitud) {
        System.out.println("Los Datos son correctos, se procede a crear solicitud");
                return generateUniqueIdSolicitud()
                        .map(ramdonId -> {
                            solicitud.setIdSolicitud(ramdonId);
                            return solicitud;
                        })
                        .flatMap(solicitudGateway::saveSolicitud)
                        .doOnSuccess(s ->System.out.println("La solicitud se ha guardado correctamente  " +  solicitud));

    }
    public Mono<String> generateUniqueIdSolicitud() {
        String randomIdSolicitud = UUID.randomUUID().toString();

        return solicitudGateway.existById(randomIdSolicitud)
                .flatMap(exists -> {
                    if (exists) {
                        return generateUniqueIdSolicitud();
                    } else {
                        System.out.println("el Id de la SOlicitud es   " + randomIdSolicitud);
                        return Mono.just(randomIdSolicitud);
                    }

                });
    }
}

