package src.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Coupon;

import java.util.List;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {
    List<Coupon> findAll();
}
