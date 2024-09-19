package src.springboot.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import src.springboot.dto.NewCompanyRequest;
import src.springboot.dto.NewCouponRequest;
import src.springboot.dto.NewCustomerRequest;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.CompanyService;
import src.springboot.service.CustomerService;
import src.springboot.utils.LoginManager;

public class Mapper {
    @Autowired
    private static CompanyService companyService;
    @Autowired
    private static CustomerService customerService;

    public static Company mapToCompany(NewCompanyRequest ncr) {
        Company company = new Company(ncr.getName(), ncr.getEmail(), ncr.getPassword(), ncr.getCoupons());
        return company;
    }

    public static Customer mapToCustomer(NewCustomerRequest ncr) {
        Customer customer = new Customer(ncr.getFirstName(), ncr.getLastName(),
                ncr.getEmail(), ncr.getPassword(), ncr.getCoupons());
        return customer;
    }

    public static Coupon mapToCoupon(NewCouponRequest ncr) throws UnAuthorizedException {
        Coupon coupon = new Coupon(companyService.getCompanyDetails(ncr.getCompanyId()), ncr.getCategory(), ncr.getTitle(),
                ncr.getDescription(), ncr.getStartDate(), ncr.getEndDate(),
                ncr.getAmount(), ncr.getPrice(), ncr.getImage());
        return coupon;
    }

}