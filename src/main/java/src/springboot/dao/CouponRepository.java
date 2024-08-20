package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    boolean existsByTitle(String couponTitle);
    void addCouponPurchase(Long couponId, Long customerId);
    void deleteCouponPurchasesByCompanyId(Long companyId);
    void detachAllCouponFromCustomer(Long customerId);

    List<Coupon> findByCompanyId(Long companyId);
}
