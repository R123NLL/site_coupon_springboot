package src.springboot.service;

import src.springboot.entities.Company;
import src.springboot.entities.Customer;

import java.util.List;

public interface AdminService {
    void deleteAll();
    Company addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Company company);
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID);
    Customer addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerID);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID);
}
