package co.com.juandaza.r2dbc.mappers.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.r2dbc.entities.SolicitudEntity;

public class SolicitudMappersImpl implements SolicitudMappers{



    @Override
    public SolicitudEntity toEntity(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setNombre(solicitud.getNombre());
        entity.setEmail(solicitud.getEmail());
        entity.setNroIdentificacion(solicitud.getNroIdentificacion());
        entity.setPrestamo(solicitud.getPrestamo());
        entity.setPlazo(solicitud.getPlazo());
        entity.setMonto(solicitud.getMonto());
        entity.setEstadoSolicitud(solicitud.getEstadoSolicitud());
        return entity;
    }

    @Override
    public Solicitud toModel(SolicitudEntity solicitudEntity) {
        Solicitud solicitud = new Solicitud();
        solicitud.setNombre(entity.getNombre());
        solicitud.setEmail(entity.getEmail());
        solicitud.setNroIdentificacion(entity.getNroIdentificacion());
        solicitud.setPrestamo(entity.getPrestamo());
        solicitud.setPlazo(entity.getPlazo());
        solicitud.setMonto(entity.getMonto());
        solicitud.setEstadoSolicitud(entity.getEstadoSolicitud());
        return solicitud;
    }
}
