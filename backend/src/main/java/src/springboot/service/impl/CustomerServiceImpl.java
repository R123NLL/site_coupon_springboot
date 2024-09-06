package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.repositories.CompanyRepository;
import src.springboot.repositories.CouponRepository;
import src.springboot.repositories.CustomerRepository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.ClientService;
import src.springboot.service.CustomerService;


import java.util.List;

import static java.util.Objects.nonNull;


@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private Long customerID;

    public CustomerServiceImpl() {

    }

    public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) {
        boolean isNotNull = nonNull(email) && nonNull(password);
        boolean isNotEmpty = !(email.isEmpty()) && !(password.isEmpty());

        if (isNotNull && isNotEmpty) {

            Customer customerByEmail = customerRepository.findByEmail(email);
            if (nonNull(customerByEmail)) {
                customerID = customerByEmail.getId();
                return customerByEmail.getPassword().equals(password);
            }
        }
        return false;
    }

    @Override
    public void purchaseCoupon(Long customerId, Long couponId) throws UnAuthorizedException {
        notLoggedIn();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));

        customer.getCoupons().add(coupon); //adding the coupon to the customer's list
        customerRepository.save(customer); //saving the updated customer
    }

    @Override
    public List<Coupon> getCustomerCoupons(Long customerId) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.findByCustomersId(customerId);

    }

    @Override
    public List<Coupon> getCustomerCoupons(Long customerId, Category category) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.findByCustomersIdAndCategory(customerId, category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Long customerId, double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.findByCustomersIdAndPriceLessThanEqual(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(Long customerId) throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (customerID <= 0) {
            throw new UnAuthorizedException("Access denied, please log in first!");
        }
    }


}
