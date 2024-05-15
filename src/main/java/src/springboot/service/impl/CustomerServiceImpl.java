package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Customer;
import src.springboot.service.CustomerService;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public void addCustomer() {

    }
}
