package src.springboot.exceptions;

public class InsufficientCouponsQuantityException extends RuntimeException {
    public InsufficientCouponsQuantityException(String message) {
        super(message);
    }
}
