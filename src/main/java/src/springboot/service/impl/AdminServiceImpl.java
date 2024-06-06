package src.springboot.service.impl;

import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Company;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.AdminService;
import src.springboot.service.ClientService;

import java.sql.SQLException;
import java.util.List;
import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    private boolean isLoggedIn = false;

    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";


    public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        this.isLoggedIn = "admin@admin.com".equalsIgnoreCase(email) && "admin".equals(password);
        if (this.isLoggedIn) {
            System.out.println("You are logged in");
        } else {
            System.out.println("Email or password incorrect, try again");
        }
        return this.isLoggedIn;
    }

    public void deleteAll() {
        customerRepository.deleteAll();
        companyRepository.deleteAll();
        couponRepository.deleteAll();
    }

    @Override
    public void addCompany(Company company) throws UnAuthorizedException {
        notLoggedIn();

        if (!this.companyRepository.isCompanyExists(company.getEmail(), company.getName())) {
            this.companyRepository.addCompany(company);
        } else {
            System.out.println("Cannot add company that already exists");
        }
    }

    @Override
    public void updateCompany(Company company) {
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
            System.out.println("Customer with " + customer.getEmail() + " already exists");
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