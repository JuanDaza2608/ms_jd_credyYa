package co.com.juandaza.model.solicitudModel.gateways;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TipoPrestamo {
    private String idPrestamo;
    private String nomPrestamo;
    private Number monMin;
    private Number monMax;
    private Number tasaInteres;
    private Boolean validacion_automatica;

}
