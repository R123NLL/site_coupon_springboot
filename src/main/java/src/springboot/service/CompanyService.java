package src.springboot.service;

import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.ArrayList;
import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon, int companyID) throws UnAuthorizedException;
    void updateCoupon(Coupon coupon) throws UnAuthorizedException;
    List<Coupon> getCompanyCoupons(int companyId) throws UnAuthorizedException;
    List<Coupon> getCompanyCoupons(int companyId,Category category) throws UnAuthorizedException;
    List<Coupon> getCompanyCoupons(int companyID,double maxPrice) throws UnAuthorizedException;
    Company getCompanyDetails(int companyID) throws UnAuthorizedException;

}
