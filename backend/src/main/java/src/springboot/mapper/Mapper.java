package src.springboot.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.springboot.dto.NewCompanyRequest;
import src.springboot.dto.NewCouponRequest;
import src.springboot.dto.NewCustomerRequest;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.CompanyService;
import src.springboot.service.CustomerService;

@Component
public class Mapper {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;

    public Company mapToCompany(NewCompanyRequest ncr) {
        return new Company(ncr.getName(), ncr.getEmail(), ncr.getPassword(), ncr.getCoupons());
    }

    public Customer mapToCustomer(NewCustomerRequest ncr) {
        return new Customer(ncr.getFirstName(), ncr.getLastName(),
                ncr.getEmail(), ncr.getPassword(), ncr.getCoupons());
    }

    public Coupon mapToCoupon(NewCouponRequest ncr) throws UnAuthorizedException {
        return new Coupon(companyService.getCompanyDetails(ncr.getCompanyId()), ncr.getCategory(), ncr.getTitle(),
                ncr.getDescription(), ncr.getStartDate(), ncr.getEndDate(),
                ncr.getAmount(), ncr.getPrice(), ncr.getImage());
    }
}
