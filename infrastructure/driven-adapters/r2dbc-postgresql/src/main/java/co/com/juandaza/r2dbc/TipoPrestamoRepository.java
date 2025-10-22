package co.com.juandaza.r2dbc;

import co.com.juandaza.r2dbc.entities.SolicitudEntity;
import co.com.juandaza.r2dbc.entities.TipoPrestamoEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TipoPrestamoRepository extends ReactiveCrudRepository<TipoPrestamoEntity,String> {
    @Query("SELECT id_tipo_prestamo, nombre, monto_minimo, monto_maximo, tasa_interes, validacion_automatica " +
            "FROM ms_jd_credy_ya.tipo_prestamo WHERE id_tipo_prestamo = :idPrestamo")
    Mono<TipoPrestamoEntity> findTipoPrestamo(String idPrestamo);
}
