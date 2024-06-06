package src.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {
    List<Coupon> findAll();

    boolean existsCompanyByEmailAndPassword(String email, String password);

    void addCoupon(Coupon coupon);

    boolean updateCoupon(Coupon coupon);

    void deleteCoupon(int couponID);

    ArrayList<Coupon> getAllCoupons();

    Coupon getOneCoupon(int couponID);

    void addCouponPurchase(int customerID, int couponID);

    void deleteCouponPurchasesByCompanyId(int companyId);

    void deleteCouponPurchase(int customerID, int couponID);

    boolean deleteCouponsByCompanyId(int companyId);

    void detachAllCouponFromCustomer(int customerId);

    boolean isCouponExist(Coupon coupon);

    ArrayList<Coupon> getAllCompanyCoupons(int companyID);

    ArrayList<Coupon> getAllCompanyCoupons(int companyID, Category category);

    ArrayList<Coupon> getCompanyCouponsBelowPrice(int companyID, double price);

    ArrayList<Coupon> getAllCustomerCoupons(int customerID);

    ArrayList<Coupon> getAllCustomerCoupons(int customerID, Category category);

    ArrayList<Coupon> getCustomerCouponsBelowPrice(int customerId, double price);


}
