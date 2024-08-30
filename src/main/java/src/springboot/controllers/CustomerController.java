package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}/coupons")
    public List<Coupon> getCustomerCoupons(@PathVariable Long customerId) throws UnAuthorizedException {
        return customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("/{customerId}/coupons/category/{category}")
    public List<Coupon> getCustomerCoupons(@PathVariable Long customerId, @PathVariable("category") Category category) throws UnAuthorizedException {
        return customerService.getCustomerCoupons(customerId, category);
    }

    @GetMapping("/{customerId}/coupons/price/{maxPrice}")
    public List<Coupon> getCustomerCoupons(@PathVariable Long customerId, @PathVariable double maxPrice) throws UnAuthorizedException {
        return customerService.getCustomerCoupons(customerId, maxPrice);
    }

    @PostMapping("/{customerId}/purchase/{couponId}")
    public ResponseEntity<String> purchaseCoupon(@PathVariable Long customerId, @PathVariable Long couponId) {
        try {
            customerService.purchaseCoupon(customerId, couponId);
            return ResponseEntity.ok("Coupon purchased succesfully");
        } catch (UnAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
