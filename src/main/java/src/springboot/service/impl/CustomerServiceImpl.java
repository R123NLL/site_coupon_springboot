package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCustomerRequest;
import src.springboot.entities.Customer;
import src.springboot.mapper.CustomerMapper;
import src.springboot.service.CustomerService;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addCustomer(NewCustomerRequest newCustomerRequest) {
        Customer customer = CustomerMapper.mapToCustomer(newCustomerRequest);
        customerRepository.save(customer);
    }
}
