package co.com.juandaza.r2dbc.mappers.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.r2dbc.entities.SolicitudEntity;

public class SolicitudMappersImpl implements SolicitudMappers{



    @Override
    public SolicitudEntity toEntity(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setIdSolicitud(solicitud.getIdSolicitud());
        entity.setEmail(solicitud.getEmail());
        entity.setPlazo(solicitud.getPlazo());
        entity.setMonto(solicitud.getMonto());
        entity.setIdEstado(solicitud.getIdEstado());
        entity.setId_tipPrestamo(solicitud.getPrestamoTipo());
        return entity;
    }

    @Override
    public Solicitud toModel(SolicitudEntity solicitudEntity) {
        Solicitud solicitud = new Solicitud();
        solicitud.setIdSolicitud(solicitudEntity.getIdSolicitud());
        solicitud.setMonto(solicitudEntity.getMonto());
        solicitud.setPlazo(solicitudEntity.getPlazo());
        solicitud.setEmail(solicitudEntity.getEmail());
        solicitud.setIdEstado(solicitudEntity.getIdEstado());
        solicitud.setPrestamoTipo(solicitudEntity.getId_tipPrestamo());
        return solicitud;
    }
}
