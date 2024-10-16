package src.springboot.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.ActiveCouponsException;
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

        boolean companyExistsByName = companyRepository.existsByName(company.getName());
        boolean companyExistsByEmail = companyRepository.existsByEmailAndPassword(company.getEmail(),company.getPassword());

        if (companyExistsByName){
            throw new DuplicateKeyException(company.getName()+" is already exists!");
        }

        if (companyExistsByEmail) {
            throw new DuplicateKeyException("The email :"+company.getEmail()+" is already registered try another!"
                    );
        }

        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company updatedCompany) throws UnAuthorizedException {
        notLoggedIn();

        Company existingCompany = companyRepository.findById(updatedCompany.getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        // Check if name is taken by another company
        if (updatedCompany.getName() != null && !updatedCompany.getName().isEmpty()) {
            if (companyRepository.existsByName(updatedCompany.getName()) &&
                    !existingCompany.getName().equals(updatedCompany.getName())) {
                throw new DuplicateKeyException("Company name already exists");
            }
            existingCompany.setName(updatedCompany.getName());
        }

        // Check if email is taken by another company
        if (updatedCompany.getEmail() != null && !updatedCompany.getEmail().isEmpty()) {
            if (companyRepository.existsByEmail(updatedCompany.getEmail()) &&
                    !existingCompany.getEmail().equals(updatedCompany.getEmail())) {
                throw new DuplicateKeyException("Email already exists");
            }
            existingCompany.setEmail(updatedCompany.getEmail());
        }

        // Update password if provided
        if (updatedCompany.getPassword() != null && !updatedCompany.getPassword().isEmpty()) {
            existingCompany.setPassword(updatedCompany.getPassword());
        }

        // Update coupons if provided
        if (updatedCompany.getCoupons() != null) {
            existingCompany.getCoupons().clear();
            existingCompany.getCoupons().addAll(updatedCompany.getCoupons());
        }

        return companyRepository.save(existingCompany);
    }



    @Override
    @Transactional
    public void deleteCompany(Long companyId) throws UnAuthorizedException {
        notLoggedIn();  // Check if the user is logged in

        // Check if the company exists
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotFoundException("Company with ID " + companyId + " not found");
        }

        // Check if the company has active coupons
        List<Coupon> activeCoupons = couponRepository.findByCompanyId(companyId);

        if (!activeCoupons.isEmpty()) {
            // Prompt the user for confirmation (throw an exception or return a message)
            throw new ActiveCouponsException("This company has " + activeCoupons.size() + " active coupons. Are you sure you want to delete it?");
        }

        // If there are no active coupons or the user confirms, proceed to delete the company
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

        boolean customerExists = customerRepository.existsCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());

        if (customerExists) {
            throw new EntityExistsException("Customer with the email " + customer.getEmail() +
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
        return couponRepository.findByStartDateLessThanEqualAndEndDateGreaterThanAndAmountGreaterThan(today,today,0);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.findByCompanyId(companyId);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (!this.isLoggedIn) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}