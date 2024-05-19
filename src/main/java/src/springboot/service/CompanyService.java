package src.springboot.service;

import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.ArrayList;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws UnAuthorizedException;
    void updateCoupon(Coupon coupon) throws UnAuthorizedException;
    void deleteCoupon(int couponId) throws UnAuthorizedException;
    ArrayList<Coupon> getCompanyCoupons() throws UnAuthorizedException;
    ArrayList<Coupon> getCompanyCoupons(Category category) throws UnAuthorizedException;
    ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws UnAuthorizedException;
    Company getCompanyDetails() throws UnAuthorizedException;

}
