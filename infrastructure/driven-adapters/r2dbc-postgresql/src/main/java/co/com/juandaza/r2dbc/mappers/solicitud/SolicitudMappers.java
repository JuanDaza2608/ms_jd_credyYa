package co.com.juandaza.r2dbc.mappers.solicitud;

import co.com.juandaza.model.solicitudModel.Solicitud;
import co.com.juandaza.r2dbc.entities.SolicitudEntity;

public interface SolicitudMappers {
    SolicitudEntity toEntity(Solicitud solicitud);
    Solicitud toModel (SolicitudEntity solicitudEntity);
}
