package co.com.juandaza.r2dbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ms_jd_credy_ya", name = "solicitud")
@Builder(toBuilder = true)
public class SolicitudEntity {

    @Id
    @Column("id_solicitud")
    private String idSolicitud;

    @Column("monto")
    private Number monto;

    @Column("plazo")
    private Number plazo;

    @Column("id_estado")
    private String idEstado;

    @Column("id_tipPrestamo")
    private String id_tipPrestamo;


}
