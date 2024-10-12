package src.springboot.exceptions;

public class ActiveCouponsException extends RuntimeException {
    public ActiveCouponsException(String message) {
        super(message);
    }
}