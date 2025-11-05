package co.com.juandaza.api.solicitud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SolicitudRouterRest {
    @Bean
    public RouterFunction<ServerResponse> solicitudrRouterFunction(SolicitudHandler handler) {
        return route(POST("/api/v1/solicitud"), handler::saveSolicitud);



    }

}
