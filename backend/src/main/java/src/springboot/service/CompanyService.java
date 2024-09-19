package src.springboot.service;


import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;


import java.util.List;

public interface CompanyService {
    List<Coupon> getCompanyCoupons(Long companyId);

    Coupon addCoupon(Coupon coupon) throws UnAuthorizedException;

    Coupon updateCoupon(Coupon coupon) throws UnAuthorizedException;

    List<Coupon> getCompanyCoupons(Long companyId, Category category) throws UnAuthorizedException;

    List<Coupon> getCompanyCoupons(Long companyID, double maxPrice) throws UnAuthorizedException;

    Company getCompanyDetails(Long companyID) throws UnAuthorizedException;

    void deleteCoupon(Long couponId) throws UnAuthorizedException;
    Long getIdByEmail(String email) throws UnAuthorizedException;
}
