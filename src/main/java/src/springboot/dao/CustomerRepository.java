package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.springboot.entities.Customer;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.email = :email AND c.password = :password")
    boolean existsCustomerByEmailAndPassword(String email, String password);

    @Query("SELECT c FROM Customer c WHERE c.id = :customerID")
    Customer getById(int customerID);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.email = :email")
    boolean isCustomerExists(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.id = :customerID")
    void deleteCustomer(int customerID);

    @Query("SELECT c FROM Customer c")
    ArrayList<Customer> getAllCustomers();

    @Query("SELECT c FROM Customer c WHERE c.id = :customerID")
    Customer getOneCustomer(int customerID);

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer getOneCustomerByEmail(String email);
}
