package src.springboot.service;

import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.List;

public interface AdminService {

    Company addCompany(Company company) throws UnAuthorizedException;

    Company updateCompany(Company company) throws UnAuthorizedException;

    void deleteCompany(Long companyId) throws UnAuthorizedException;

    List<Company> getAllCompanies() throws UnAuthorizedException;

    Company getOneCompany(Long companyId) throws UnAuthorizedException;

    Customer addCustomer(Customer customer) throws UnAuthorizedException;

    Customer updateCustomer(Customer customer) throws UnAuthorizedException;

    void deleteCustomer(Long customerId) throws UnAuthorizedException;

    List<Customer> getAllCustomers() throws UnAuthorizedException;

    Customer getOneCustomer(Long customerId) throws UnAuthorizedException;

    List<Coupon> getAllActiveCoupons();

}
