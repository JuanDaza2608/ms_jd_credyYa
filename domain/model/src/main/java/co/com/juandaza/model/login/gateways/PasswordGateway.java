package co.com.juandaza.model.login.gateways;

public interface PasswordGateway {
    String encrypt(String plainPassword);
    boolean matches(String plainPassword, String hashedPassword);
}

