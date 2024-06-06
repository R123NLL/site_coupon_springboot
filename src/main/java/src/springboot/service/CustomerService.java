package src.springboot.service;


import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    void purchaseCoupon(int customerID, Coupon coupon) throws UnAuthorizedException;

    List<Coupon> getCustomerCoupons(int customerID) throws UnAuthorizedException;

    List<Coupon> getCustomerCoupons(int customerID, Category category) throws UnAuthorizedException;

    List<Coupon> getCustomerCoupons(int customerID, double maxPrice) throws UnAuthorizedException;

    Customer getCustomerDetails(int customerID) throws UnAuthorizedException;
}
