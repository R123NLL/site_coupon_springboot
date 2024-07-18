package src.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.Category;
import src.springboot.entities.ClientType;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.service.CustomerService;

import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping("/customer")
public class CustomerController extends ClientController {
    @Autowired
    private CustomerService customerService;

    public CustomerController() {

    }

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean login(String email, String password) {
        return super.login(new NewLoginRequest(email, password, getClientType()));
    }

    public void purchaseCoupon(Coupon coupon) {

    }

    public ArrayList<Coupon> getCustomerCoupons() {

    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) {

    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {

    }

    public Customer getCustomerDetails() {

    }


    @Override
    protected ClientType getClientType() {
        return ClientType.Customer;
    }
}
