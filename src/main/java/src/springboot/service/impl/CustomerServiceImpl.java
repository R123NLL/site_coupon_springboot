package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.ClientService;
import src.springboot.service.CustomerService;


import java.sql.SQLException;
import java.util.List;

import static java.util.Objects.nonNull;


@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private int customerID;

    public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) {
        boolean isNotNull = nonNull(email) && nonNull(password);
        boolean isNotEmpty = !(email.isEmpty()) && !(password.isEmpty());

        if (isNotNull && isNotEmpty) {

            Customer customerByEmail = customerRepository.getOneCustomerByEmail(email);
            if (nonNull(customerByEmail)) {
                customerID = customerByEmail.getId();
                return customerByEmail.getPassword().equals(password);
            }
        }
        return false;
    }

    @Override
    public void purchaseCoupon(int customerID, Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();
        if (nonNull(coupon)) {
            couponRepository.addCouponPurchase(customerID, coupon.getId());
        }
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCustomerCoupons(customerID);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID, Category category) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCustomerCoupons(customerID, category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID, double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getCustomerCouponsBelowPrice(customerID, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerID) throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.getOneCustomer(customerID);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (customerID <= 0) {
            throw new UnAuthorizedException("Access denied, please log in first!");
        }
    }


}
