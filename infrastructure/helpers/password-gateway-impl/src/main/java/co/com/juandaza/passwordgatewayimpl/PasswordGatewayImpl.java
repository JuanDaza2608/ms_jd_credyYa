package co.com.juandaza.passwordgatewayimpl;

import co.com.juandaza.model.login.gateways.PasswordGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGatewayImpl implements PasswordGateway {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encrypt(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    @Override
    public boolean matches(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }}
