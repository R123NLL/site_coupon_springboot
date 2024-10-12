package src.springboot.service;


import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    void purchaseCoupon(Long customerId, Long couponId) throws UnAuthorizedException;

    void removePurchasedCoupon(Long customerId, Long couponId) throws UnAuthorizedException;

    List<Coupon> getCustomerCoupons(Long customerID) throws UnAuthorizedException;

    List<Coupon> getCustomerCoupons(Long customerID, Category category) throws UnAuthorizedException;

    List<Coupon> getCustomerCoupons(Long customerID, double maxPrice) throws UnAuthorizedException;

    Customer getCustomerDetails(Long customerID) throws UnAuthorizedException;

    Long getIdByEmail(String email) throws UnAuthorizedException;


}
