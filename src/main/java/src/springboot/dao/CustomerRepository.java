package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Customer;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByEmailAndPassword(String email, String password);
    List<Customer> getAllCustomers();
    Customer findByEmail(String email);
}
