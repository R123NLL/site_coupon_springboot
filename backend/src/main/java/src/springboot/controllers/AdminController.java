package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.springboot.dto.NewCompanyRequest;
import src.springboot.dto.NewCustomerRequest;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.Company;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.mapper.Mapper;
import src.springboot.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController extends ClientController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private Mapper mapper;

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody NewLoginRequest newLoginRequest) {
        return super.login(newLoginRequest);
    }

    @PostMapping("/companies")
    public Company addCompany(@RequestBody NewCompanyRequest newCompanyRequest) throws UnAuthorizedException {
        Company company =  mapper.mapToCompany(newCompanyRequest);

        return adminService.addCompany(company);
    }

    @PutMapping("/companies/{companyId}")
    public Company updateCompany(@PathVariable Long companyId ,@RequestBody NewCompanyRequest newCompanyRequest) throws UnAuthorizedException {
        Company company = mapper.mapToCompany(newCompanyRequest);

        return adminService.updateCompany(company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void deleteCompany(@PathVariable Long companyId) throws UnAuthorizedException {
        adminService.deleteCompany(companyId);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() throws UnAuthorizedException {
        return adminService.getAllCompanies();
    }

    @GetMapping("/companies/{companyId}")
    public Company getOneCompany(@PathVariable Long companyId) throws UnAuthorizedException {

        return adminService.getOneCompany(companyId);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody NewCustomerRequest newCustomerRequest) throws UnAuthorizedException {
        Customer customer = mapper.mapToCustomer(newCustomerRequest);

        return adminService.addCustomer(customer);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody NewCustomerRequest newCustomerRequest) throws UnAuthorizedException {
        Customer customer = mapper.mapToCustomer(newCustomerRequest);

        return adminService.updateCustomer(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) throws UnAuthorizedException {
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() throws UnAuthorizedException {
        return adminService.getAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getOneCustomer(@PathVariable Long customerId) throws UnAuthorizedException {
        return adminService.getOneCustomer(customerId);
    }


}
