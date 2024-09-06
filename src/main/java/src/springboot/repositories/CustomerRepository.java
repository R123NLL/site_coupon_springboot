package src.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByEmailAndPassword(String email, String password);
    Customer findByEmail(String email);
}
