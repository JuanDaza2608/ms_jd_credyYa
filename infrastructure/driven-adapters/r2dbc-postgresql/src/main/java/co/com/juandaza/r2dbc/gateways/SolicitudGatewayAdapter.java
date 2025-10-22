package co.com.juandaza.r2dbc.gateways;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.model.solicitudModel.gateways.SolicitudGateway;
import co.com.juandaza.model.solicitudModel.gateways.TipoPrestamo;
import co.com.juandaza.model.user.User;
import co.com.juandaza.r2dbc.TipoPrestamoRepository;
import co.com.juandaza.r2dbc.entities.TipoPrestamoEntity;
import co.com.juandaza.r2dbc.mappers.solicitud.SolicitudMappers;
import co.com.juandaza.r2dbc.solicitud.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class SolicitudGatewayAdapter implements SolicitudGateway {
    private final SolicitudRepository solicitudRepository;
    private final TipoPrestamoRepository tipoPrestamoRepository;


    @Override
    public Mono<TipoPrestamo> getTipoPrestamo(String idPrestamo) {
        return tipoPrestamoRepository.findTipoPrestamo(idPrestamo)
                .map(entity -> new TipoPrestamo(
                        entity.getIdPrestamo(),
                        entity.getNomPrestamo(),
                        entity.getMonMin(),
                        entity.getMonMax(),
                        entity.getTasaInteres(),
                        entity.getValAutomatica()
                ));
    }

    @Override
    public Mono<Solicitud> saveSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(mapper.toEntity(solicitud))
                .map(mapper::toDomain);
    }
}
