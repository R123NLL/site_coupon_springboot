package src.springboot.service.impl;

import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Company;
import src.springboot.entities.Customer;
import src.springboot.service.AdminService;
import src.springboot.service.ClientService;

import java.sql.SQLException;
import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";

    public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
        companyRepository.deleteAll();
        couponRepository.deleteAll();
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return false;
    }

    @Override
    public Company addCompany(Company company) {
        return null;
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(Company company) {

    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getOneCompany(int companyID) {
        return null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerID) {

    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return null;
    }
}
