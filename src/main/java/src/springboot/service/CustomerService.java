package src.springboot.service;


import src.springboot.dto.NewCustomerRequest;
import src.springboot.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    void addCustomer(NewCustomerRequest newCustomerRequest);
}
