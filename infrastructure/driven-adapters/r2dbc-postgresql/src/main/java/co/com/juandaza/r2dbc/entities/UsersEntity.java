package co.com.juandaza.r2dbc.entities;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ms_jd_credy_ya", name = "usuarios")
@Builder(toBuilder = true)
public class UsersEntity {

    @Id
    @Column("id_usuario")
    private String idUsuario;

    @Column("nombre")
    private String nombre;

    @Column("apellido")
    private String apellido;

    @Column("email")
    private String email;

    @Column("documento_identidad")
    private String nroIdentificacion;

    @Column("telefono")
    private String telefono;

    @Column("id_Rol")
    private String idRol;

    @Column("salario_base")
    private Number salarioBase;

//    @Column(name = "Fecha_nacimiento")
//    private String birthDate;
}
