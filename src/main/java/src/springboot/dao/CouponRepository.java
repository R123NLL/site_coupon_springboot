package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAll();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Coupon c WHERE c.title = :couponTitle")
    boolean existsCouponByTitle(String couponTitle);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO coupon_purchase (coupon_id, customer_id) VALUES (:couponId, :customerId)", nativeQuery = true)
    void addCouponPurchase(@Param("couponId") int couponId, @Param("customerId") int customerId);

    @Query("SELECT c FROM Coupon c")
    List<Coupon> findAllCoupons();

    @Modifying
    @Transactional
    @Query("DELETE FROM Coupon c WHERE c.id = ?1")
    void deleteCoupon(int couponID);

    @Query("SELECT c FROM Coupon c WHERE c.id = ?1")
    Coupon getOneCoupon(int couponID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM coupon_purchase WHERE coupon_id IN (SELECT id FROM coupon WHERE company_id = :companyId)", nativeQuery = true)
    void deleteCouponPurchasesByCompanyId(@Param("companyId") int companyId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Coupon c WHERE c.company.id = ?1")
    void deleteCouponsByCompanyId(int companyId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM coupon_purchase WHERE customer_id = :customerId", nativeQuery = true)
    void detachAllCouponFromCustomer(@Param("customerId") int customerId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Coupon c WHERE c = ?1")
    boolean isCouponExist(Coupon coupon);

    @Query("SELECT c FROM Coupon c WHERE c.company.id = ?1")
    List<Coupon> getAllCompanyCoupons(int companyID);

    @Query("SELECT c FROM Coupon c WHERE c.company.id = ?1 AND c.category = ?2")
    ArrayList<Coupon> getAllCompanyCoupons(int companyID, Category category);

    @Query("SELECT c FROM Coupon c WHERE c.company.id = ?1 AND c.price < ?2")
    List<Coupon> getCompanyCouponsBelowPrice(int companyID, double price);

    @Query("SELECT c FROM Coupon c JOIN c.customers cust WHERE cust.id = :customerId")
    List<Coupon> getAllCustomerCoupons(@Param("customerId") int customerId);

    @Query("SELECT c FROM Coupon c JOIN c.customers cust WHERE cust.id = :customerId AND c.category = :category")
    List<Coupon> getAllCustomerCoupons(@Param("customerId") int customerId, @Param("category") Category category);


    @Query("SELECT c FROM Coupon c JOIN c.customers cust WHERE cust.id = :customerId AND c.price < :price")
    List<Coupon> getCustomerCouponsBelowPrice(@Param("customerId") int customerId, @Param("price") double price);

}
