package co.com.juandaza.r2dbc.mappers.users;

import co.com.juandaza.model.user.User;
import co.com.juandaza.r2dbc.entities.UsersEntity;

public interface UsersMappers {
    UsersEntity toEntity(User users);
    User toModel(UsersEntity usersEntity);

}
