package src.springboot.exceptions;

public class LoginSecurityException extends Exception{
    public LoginSecurityException(){

    }

    public LoginSecurityException(String message){
        super(message);
    }
}
