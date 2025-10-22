package co.com.juandaza.usecase.util.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageError {
    private int status;
    private String message;
    private String path;
}
