package src.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByEmailAndPassword(String email, String password);
    Customer findByEmail(String email);

    @Query("SELECT c FROM Coupon c WHERE c.id NOT IN (SELECT cp.id FROM Customer cust JOIN cust.coupons cp WHERE cust.id = :customerId)")
    List<Coupon> findUnboughtCouponsByCustomerId(Long customerId);
}
