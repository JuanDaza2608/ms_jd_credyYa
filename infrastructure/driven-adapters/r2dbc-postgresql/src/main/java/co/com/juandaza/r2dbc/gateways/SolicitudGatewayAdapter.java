package co.com.juandaza.r2dbc.gateways;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.solicitudModel.gateways.SolicitudGateway;
import co.com.juandaza.model.solicitudModel.gateways.TipoPrestamo;
import co.com.juandaza.model.user.User;
import co.com.juandaza.r2dbc.solicitud.SolicitudRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class SolicitudGatewayAdapter implements SolicitudGateway {
    private final SolicitudRepository solicitudRepository;

    @Override
    public Mono<Solicitud> saveSolicitud(Solicitud solicitud) {
        return solicitudRepository.insertSolicitud(
                solicitud.getIdSolicitud(),
                solicitud.getMonto().toString(),
                solicitud.getPlazo().toString(),
                solicitud.getEmail(),
                solicitud.getIdEstado(),
                solicitud.getPrestamoTipo()
        ).thenReturn(solicitud);    }

    @Override
    public Mono<User> validateUser(String email, String numDoc) {
        return solicitudRepository.findUserByEmailId(email,numDoc);
    }

    @Override
    public Mono<Boolean> validateLoanType(String loandType) {
        return solicitudRepository.findLoandType(loandType);
    }

    @Override
    public Mono<Boolean> existById(String idSolicitud) {
        return solicitudRepository.existsById(idSolicitud);
    }
}
