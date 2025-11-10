package co.com.juandaza.usecase.util;

public class BusinessException extends RuntimeException{

    private String code;
    public BusinessException(String code, String message){
        super(message);
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
