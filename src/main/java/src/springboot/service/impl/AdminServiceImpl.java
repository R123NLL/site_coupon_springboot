package src.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.AdminService;
import src.springboot.service.ClientService;
import src.springboot.test.AdminTester;

import java.sql.SQLException;
import java.util.List;
import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    private boolean isLoggedIn;
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);



    public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        this.isLoggedIn = EMAIL.equalsIgnoreCase(email) && PASSWORD.equals(password);
        if (this.isLoggedIn) {
            logger.info("You are logged in");
        } else {
            logger.error("Email or password incorrect, try again");
        }
        return this.isLoggedIn;
    }

    public void deleteAll() throws UnAuthorizedException {
        notLoggedIn();
        customerRepository.deleteAll();
        companyRepository.deleteAll();
        couponRepository.deleteAll();
        logger.info("*All repositories have been deleted*");
    }

    @Override
    public void addCompany(Company company) throws UnAuthorizedException {
        notLoggedIn();

        if (!this.companyRepository.isCompanyExists(company.getEmail(), company.getName())) {
            this.companyRepository.addCompany(company);
        } else {
            logger.error("Cannot add company that already exists");
        }
    }

    @Override
    public void updateCompany(Company company) throws UnAuthorizedException {
        notLoggedIn();
        this.companyRepository.save(company);
    }


    @Override
    public void deleteCompany(int companyId) throws UnAuthorizedException {

        notLoggedIn();

        // Delete coupon purchase history
        this.couponRepository.deleteCouponPurchasesByCompanyId(companyId);

        // Delete coupons
        this.couponRepository.deleteCouponsByCompanyId(companyId);

        // Delete company
        this.companyRepository.deleteCompany(companyId);
    }

    @Override
    public List<Company> getAllCompanies() throws UnAuthorizedException {
        notLoggedIn();
        return this.companyRepository.getAllCompanies();
    }

    @Override
    public Company getOneCompany(int companyId) throws UnAuthorizedException {
        notLoggedIn();
        return this.companyRepository.getOneCompany(companyId);
    }

    @Override
    public void addCustomer(Customer customer) throws UnAuthorizedException {
        notLoggedIn();
        if (!this.customerRepository.isCustomerExists(customer.getEmail())) {
            this.customerRepository.save(customer);
        } else {
            logger.error("Customer with " + customer.getEmail() + " already exists");
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws UnAuthorizedException {
        notLoggedIn();
        this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws UnAuthorizedException {
        notLoggedIn();
        this.couponRepository.detachAllCouponFromCustomer(customerId);
        this.customerRepository.deleteCustomer(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.getAllCustomers();
    }

    @Override
    public List<Coupon> getAllCoupons() throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.findAllCoupons();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws UnAuthorizedException {
        notLoggedIn();
        return this.customerRepository.getOneCustomer(customerId);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (!this.isLoggedIn) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}