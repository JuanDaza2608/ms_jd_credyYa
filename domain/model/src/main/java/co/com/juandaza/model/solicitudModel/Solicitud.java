package co.com.juandaza.model.solicitudModel;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Solicitud {
    private String idSolicitud;
    private Number monto;
    private Number plazo;
    private String email;
    private String idEstado;
    private String prestamoTipo;
    private String nroIdentificacion;




}
