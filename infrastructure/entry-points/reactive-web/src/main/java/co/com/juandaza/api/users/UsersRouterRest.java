package co.com.juandaza.api.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UsersRouterRest {
    @Bean
    public RouterFunction<ServerResponse> usersRouterFunction(UsersHandler handler) {
        return route(GET("/api/v1/usuariosConsulta"), handler::getUsers)
                .andRoute(POST("/api/v1/usuarios"), handler::saveUsers);

    }
}
