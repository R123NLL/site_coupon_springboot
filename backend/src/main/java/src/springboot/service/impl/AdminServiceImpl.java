package src.springboot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.entities.Coupon;
import src.springboot.repositories.CompanyRepository;
import src.springboot.repositories.CouponRepository;
import src.springboot.repositories.CustomerRepository;
import src.springboot.entities.Company;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.AdminService;
import src.springboot.service.ClientService;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    private boolean isLoggedIn;
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl() {

    }

    public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        this.isLoggedIn = EMAIL.equalsIgnoreCase(email) && PASSWORD.equals(password);
        if (this.isLoggedIn) {
            logger.info("Logged is successfully, Welcome back administrator");
        } else {
            logger.error("Login failed: Email or password are incorrect, try again");
        }
        return this.isLoggedIn;
    }

    public Long getAdminId(){
        return 1L;
    }


    @Override
    public Company addCompany(Company company) throws UnAuthorizedException {
        notLoggedIn();

        boolean companyExists = companyRepository.existsByEmailAndPassword(company.getEmail(),company.getPassword());

        if (companyExists) {
            throw new IllegalArgumentException("Company with the name " + company.getName() +
                    " is already exists");
        }
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company updatedCompany) throws UnAuthorizedException {
        notLoggedIn();

        Company existingCompany = companyRepository.findById(updatedCompany.getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setEmail(updatedCompany.getEmail());
        existingCompany.setPassword(updatedCompany.getPassword());
        existingCompany.setCoupons(updatedCompany.getCoupons());

        return companyRepository.save(existingCompany);
    }

    @Override
    @Transactional
    public void deleteCompany(Long companyId) throws UnAuthorizedException {
        notLoggedIn();

        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotFoundException("Company with ID " + companyId + " not found");
        }

        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() throws UnAuthorizedException {
        notLoggedIn();
        return this.companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(Long companyId) throws UnAuthorizedException {
        notLoggedIn();
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
    }

    @Override
    public Customer addCustomer(Customer customer) throws UnAuthorizedException {
        notLoggedIn();

        boolean customerExists = customerRepository.existsCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());

        if (customerExists) {
            throw new IllegalArgumentException("Customer with the email " + customer.getEmail() +
                    " is already exists");
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) throws UnAuthorizedException {
        notLoggedIn();

        Customer existingCustomer = customerRepository.findById(updatedCustomer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPassword(updatedCustomer.getPassword());
        existingCustomer.setCoupons(updatedCustomer.getCoupons());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) throws UnAuthorizedException {
        notLoggedIn();

        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotFoundException("Customer with ID " + customerId + " not found");
        }

        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(Long customerId) throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public List<Coupon> getAllActiveCoupons(){
        LocalDate today = LocalDate.now();
        return couponRepository.findByEndDateAfter(today);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (!this.isLoggedIn) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}