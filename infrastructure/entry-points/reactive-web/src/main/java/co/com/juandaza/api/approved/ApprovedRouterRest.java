package co.com.juandaza.api.approved;

import co.com.juandaza.api.users.UsersHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ApprovedRouterRest {

    @Bean
    public RouterFunction<ServerResponse> approvedRouterFunction(ApprovedHandler handler) {
        return route(POST("/api/v1/aprobacion"), handler::getApproved);
               // .andRoute(POST("/api/v1/usuarios"), handler::saveUsers);


    }
}
