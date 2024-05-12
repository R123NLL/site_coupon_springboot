package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {
    Coupon findById(int couponID);

    boolean existsByTitle(String title);

    boolean existsByTitleAndCompanyId(String title, int companyId);

    boolean existsByIdAndCompanyId(int companyId, int couponId);
    ArrayList<Coupon> findAllByCompanyId(int companyId);

    ArrayList<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

    ArrayList<Coupon> findAllByCompanyIdAndPriceLessThanEqual(int companyId, double price);
    List<Coupon> findAll();

}
