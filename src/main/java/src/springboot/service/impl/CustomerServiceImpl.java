package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCustomerRequest;
import src.springboot.entities.Customer;
import src.springboot.mapper.CustomerMapper;
import src.springboot.service.ClientService;
import src.springboot.service.CustomerService;


import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addCustomer(NewCustomerRequest newCustomerRequest) {
        Customer customer = CustomerMapper.mapToCustomer(newCustomerRequest);
        customerRepository.save(customer);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        if (!customerRepository.existsCustomerByEmailAndPassword(email, password)) {
         //todo exception   //throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND);
            throw new SQLException("not found");
        }
        //this.customerID = customerRepository.getCustomerID(email, password);
        return true;
    }
}
