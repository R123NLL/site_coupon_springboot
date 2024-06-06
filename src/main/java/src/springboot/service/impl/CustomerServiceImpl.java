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


    public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return customerRepository.existsCustomerByEmailAndPassword(email, password);
    }

    @Override
    public void purchaseCoupon(int customerID, Coupon coupon) throws UnAuthorizedException {

        if (nonNull(coupon)) {
            couponRepository.addCouponPurchase(customerID, coupon.getId());
        }
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID) throws UnAuthorizedException {

        return customerRepository.getById(customerID).getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID, Category category) throws UnAuthorizedException {

        return couponRepository.getAllCustomerCoupons(customerID, category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID, double maxPrice) throws UnAuthorizedException {

        return couponRepository.getCustomerCouponsBelowPrice(customerID, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerID) throws UnAuthorizedException {

        return customerRepository.getOneCustomer(customerID);
    }


}
