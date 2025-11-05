package co.com.juandaza.api.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class ErrorResponse {
    private String codigo;
    private String mensaje;
    private String detalle;
    private String timestamp;
}
