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
import java.util.ArrayList;

import static java.util.Objects.nonNull;


@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private final int customerID;

    public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository, int customerID) {
        super(companyRepository, customerRepository, couponRepository);
        this.customerID = customerID;
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return customerRepository.existsCustomerByEmailAndPassword(email, password);
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();
        if (nonNull(coupon)) {
            couponRepository.addCouponPurchase(customerID, coupon.getId());
        }
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(int customerID) throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.getById(customerID).getCoupons();
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(Category category) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCustomerCoupons(customerID, category);
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getCustomerCouponsBelowPrice(customerID, maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws UnAuthorizedException {
        notLoggedIn();
        return customerRepository.getOneCustomer(customerID);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        notLoggedIn();
        if (customerID <= 0) {
            throw new UnAuthorizedException("Access denied, please log in first!");
        }
    }

}
