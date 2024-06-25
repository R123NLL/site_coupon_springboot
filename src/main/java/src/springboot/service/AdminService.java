package src.springboot.service;

import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;

import java.util.List;

public interface AdminService {
    void deleteAll() throws UnAuthorizedException;

    void addCompany(Company company) throws UnAuthorizedException;

    void updateCompany(Company company) throws UnAuthorizedException;

    void deleteCompany(int companyId) throws UnAuthorizedException;

    List<Company> getAllCompanies() throws UnAuthorizedException;

    Company getOneCompany(int companyId) throws UnAuthorizedException;

    void addCustomer(Customer customer) throws UnAuthorizedException;

    void updateCustomer(Customer customer) throws UnAuthorizedException;

    void deleteCustomer(int customerId) throws UnAuthorizedException;

    List<Customer> getAllCustomers() throws UnAuthorizedException;

    List<Coupon> getAllCoupons() throws UnAuthorizedException;


    Customer getOneCustomer(int customerId) throws UnAuthorizedException;

}
