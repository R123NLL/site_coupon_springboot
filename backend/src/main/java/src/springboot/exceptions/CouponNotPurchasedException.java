package src.springboot.exceptions;

public class CouponNotPurchasedException extends RuntimeException {
    public CouponNotPurchasedException(String message) {
        super(message);
    }
}