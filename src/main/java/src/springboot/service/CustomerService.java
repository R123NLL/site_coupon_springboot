package src.springboot.service;

import src.springboot.entities.Company;
import src.springboot.entities.Customer;

import java.util.ArrayList;

public interface CustomerService {
    ArrayList<Customer> getAllCustomers();

    void addCustomer();
}
