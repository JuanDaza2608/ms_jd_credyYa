package co.com.juandaza.model.user;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String nroIdentificacion;
    private String telefono;
    private String idRol;
    private Number salarioBase;
//   private String birthDate;
}
