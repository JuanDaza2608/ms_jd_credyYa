package co.com.juandaza.r2dbc.mappers.users;

import co.com.juandaza.model.user.User;
import co.com.juandaza.r2dbc.entities.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersMappersImpl implements UsersMappers{

    @Override
    public UsersEntity toEntity(User users) {
        UsersEntity entity = new UsersEntity();
        entity.setIdUsuario(users.getIdUsuario());
        entity.setNombre(users.getNombre());
        entity.setApellido(users.getApellido());
        entity.setEmail(users.getEmail());
        entity.setNroIdentificacion(users.getNroIdentificacion());
        entity.setTelefono(users.getTelefono());
        entity.setIdRol(users.getIdRol());
        entity.setSalarioBase(users.getSalarioBase());
        return entity;
    }

    @Override
    public User toModel(UsersEntity usersEntity) {
        return User.builder()
                .idUsuario(usersEntity.getIdUsuario())
                .nombre(usersEntity.getNombre())
                .apellido(usersEntity.getApellido())
                .email(usersEntity.getEmail())
                .nroIdentificacion(usersEntity.getNroIdentificacion())
                .telefono(usersEntity.getTelefono())
                .idRol(usersEntity.getIdRol())
                .salarioBase(usersEntity.getSalarioBase())
                .build();
    }

}
