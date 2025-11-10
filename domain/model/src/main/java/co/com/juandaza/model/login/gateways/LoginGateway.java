package co.com.juandaza.model.login.gateways;

import co.com.juandaza.model.login.Login;
import reactor.core.publisher.Mono;

public interface LoginGateway {

    public Mono<Boolean>  validateUserLogin(String user, String Password);
}
