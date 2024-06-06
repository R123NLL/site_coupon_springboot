package src.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Customer;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();

    boolean existsCustomerByEmailAndPassword(String email, String password);

    Customer getById(int customerID);
    boolean isCustomerExists(String email);

    void addCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    void deleteCustomer(int customerID);

    ArrayList<Customer> getAllCustomers();

    Customer getOneCustomer(int customerID);
    Customer getOneCustomerByEmail(String email);
}
