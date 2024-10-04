package src.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    boolean existsByTitleAndCompanyId(String title, Long companyId);

    List<Coupon> findByCompanyId(Long companyId);

    List<Coupon> findByCompanyIdAndCategory(Long companyId, Category category);

    List<Coupon> findByCompanyIdAndPriceLessThanEqual(Long companyId, double maxPrice);

    List<Coupon> findByCustomersId(Long customerId);

    List<Coupon> findByCustomersIdAndCategory(Long customerId, Category category);

    List<Coupon> findByCustomersIdAndPriceLessThanEqual(Long customerId, double maxPrice);

    List<Coupon>  findByStartDateLessThanEqualAndEndDateGreaterThanAndAmountGreaterThan(LocalDate currentDate, LocalDate currentDateAgain, int amount);

}
