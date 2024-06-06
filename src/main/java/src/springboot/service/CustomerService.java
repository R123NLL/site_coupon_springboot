package src.springboot.service;


import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.ArrayList;

public interface CustomerService {
    void purchaseCoupon(Coupon coupon) throws UnAuthorizedException;

    ArrayList<Coupon> getCustomerCoupons(int customerID) throws UnAuthorizedException;
    ArrayList<Coupon> getCustomerCoupons(Category category) throws UnAuthorizedException;

    ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws UnAuthorizedException;

    Customer getCustomerDetails() throws UnAuthorizedException;
}
