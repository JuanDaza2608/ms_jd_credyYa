package co.com.juandaza.r2dbc.util;

import co.com.juandaza.r2dbc.entities.UsersEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class GetUsers {
    public static UsersEntity getUserEntity(io.r2dbc.spi.Readable row){
        return UsersEntity.builder()
                .idUsuario(row.get("id_usuario", String.class))
                .nombre(row.get("nombre", String.class))
                .apellido(row.get("apellido", String.class))
                .email(row.get("email", String.class))
                .nombre(row.get("documento_identidad", String.class))
                .telefono(row.get("telefono", String.class))
                .idRol(row.get("id_rol", String.class))
                .salarioBase(row.get("salario_base", Number.class))
                .build();
    }

}
