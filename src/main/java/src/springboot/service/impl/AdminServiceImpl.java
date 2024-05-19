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

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";

    public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return false;
    }

    public void deleteAll() {
        customerRepository.deleteAll();
        companyRepository.deleteAll();
        couponRepository.deleteAll();
    }

    @Override
    public void addCompany(Company company) throws UnAuthorizedException {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(int companyId) throws UnAuthorizedException {

    }

    @Override
    public List<Company> getAllCompanies() throws UnAuthorizedException {
        return null;
    }

    @Override
    public Company getOneCompany(int companyId) throws UnAuthorizedException {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) throws UnAuthorizedException {

    }

    @Override
    public void updateCustomer(Customer customer) throws UnAuthorizedException {

    }

    @Override
    public void deleteCustomer(int customerId) throws UnAuthorizedException {

    }

    @Override
    public List<Customer> getAllCustomers() throws UnAuthorizedException {
        return null;
    }

    @Override
    public Customer getOneCustomer(int customerId) throws UnAuthorizedException {
        return null;
    }


}
