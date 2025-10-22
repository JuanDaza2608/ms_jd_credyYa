package co.com.juandaza.r2dbc.solicitud;

import co.com.juandaza.model.solicitudModel.gateways.TipoPrestamo;
import co.com.juandaza.r2dbc.entities.SolicitudEntity;
import co.com.juandaza.r2dbc.entities.TipoPrestamoEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SolicitudRepository extends ReactiveCrudRepository<SolicitudEntity,String> {

}
