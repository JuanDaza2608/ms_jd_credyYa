package co.com.juandaza.usecase.login;

import co.com.juandaza.model.login.Login;
import co.com.juandaza.model.login.gateways.LoginGateway;
import co.com.juandaza.model.login.gateways.PasswordGateway;
import co.com.juandaza.usecase.util.BusinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoginUseCase {

    private final PasswordGateway passwordGateway;
    private final LoginGateway loginGateway;

    public Mono<Boolean> validateUserLogin(Login login){
        System.out.println("Entrando a validar Login de usuario con --- USUARIO:  " + login.getUserLogin() + "   Y CONTRASEÑA: " + login.getPasswordLogin());
        return loginGateway.validateUserLogin(login.getUserLogin(), login.getPasswordLogin())
                    .switchIfEmpty(Mono.error(new BusinessException("USR-404", "Usuario y/o contraseña no valido")))
                .flatMap(usuario -> {
                    boolean valido = passwordGateway.matches(login.getPasswordLogin(), login.getPasswordLogin());
                    if (!valido) {
                        return Mono.error(new BusinessException("USR-404", "Usuario y/o contraseña no valido"));
                    }
                    return Mono.just(usuario);
                });
    }
}
