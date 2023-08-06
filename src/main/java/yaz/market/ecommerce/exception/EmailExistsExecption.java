package yaz.market.ecommerce.exception;

public class EmailExistsExecption extends RuntimeException{
    public EmailExistsExecption(String message){
        super(message);
    }
}
