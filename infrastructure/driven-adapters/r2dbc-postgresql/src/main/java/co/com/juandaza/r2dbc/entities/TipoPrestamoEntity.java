package co.com.juandaza.r2dbc.entities;

import org.springframework.data.relational.core.mapping.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ms_jd_credy_ya", name = "tipo_prestamo")
public class TipoPrestamoEntity {
    @Id
    @Column("id_tipo_prestamo")
    private String idPrestamo;

    @Column("nombre")
    private String nomPrestamo;

    @Column("monto_minimo")
    private Number monMin;

    @Column("monto_maximo")
    private Number monMax;

    @Column("tasa_interes")
    private Number tasaInteres;

    @Column("validacion_automatica")
    private Boolean valAutomatica;
}
