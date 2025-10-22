package co.com.juandaza.model.solicitudModel;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Solicitud {
    private String nombre;
    private String email;
    //private String idSolicitud; validat si lo necesito por que se genera
    private String nroIdentificacion;
    private String prestamo;
    private Number plazo;
    private Number monto;


}
