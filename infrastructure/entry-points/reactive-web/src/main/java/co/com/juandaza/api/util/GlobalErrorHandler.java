package co.com.juandaza.api.util;

import co.com.juandaza.usecase.util.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


@Component
public class GlobalErrorHandler implements WebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        var response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        if (ex instanceof BusinessException) {
            log.error("Error de negocio: {}", ex.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            String body = "{ \"error\": \"" + ex.getMessage() + "\" }";
            DataBuffer buffer = response.bufferFactory()
                    .wrap(body.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }

        log.error("Error inesperado: ", ex);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        String body = "{ \"error\": \"Error interno del servidor\" }";
        DataBuffer buffer = response.bufferFactory()
                .wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}