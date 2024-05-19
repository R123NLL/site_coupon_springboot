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


@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return false;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws UnAuthorizedException {

    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons() throws UnAuthorizedException {
        return null;
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(Category category) throws UnAuthorizedException {
        return null;
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws UnAuthorizedException {
        return null;
    }

    @Override
    public Customer getCustomerDetails() throws UnAuthorizedException {
        return null;
    }
}
