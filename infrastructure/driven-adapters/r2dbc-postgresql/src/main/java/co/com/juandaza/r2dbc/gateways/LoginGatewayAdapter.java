package co.com.juandaza.r2dbc.gateways;

import co.com.juandaza.model.login.Login;
import co.com.juandaza.model.login.gateways.LoginGateway;
import co.com.juandaza.r2dbc.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginGatewayAdapter implements LoginGateway {
    private final LoginRepository loginRepository;


    @Override
    public Mono<Boolean>  validateUserLogin(String user, String Password) {
        return loginRepository.validateUserLogin(user,Password);
    }
}
