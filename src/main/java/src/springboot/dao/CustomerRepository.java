package src.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    boolean existsCustomerByEmailAndPassword(String email, String password);
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerByEmailAndIdIsNot(String email,int id);
    List<Customer> findAll();

}
