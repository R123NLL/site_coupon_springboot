package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.Category;
import src.springboot.entities.ClientType;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.InsufficientCouponsQuantityException;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.CustomerService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController extends ClientController {
    @Autowired
    private CustomerService customerService;


    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody NewLoginRequest newLoginRequest) {
        return super.login(newLoginRequest);
    }

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

    @PostMapping("/{customerId}/purchase/{couponId}/{quantity}")
    public ResponseEntity<String> purchaseCoupon(@PathVariable Long customerId, @PathVariable Long couponId, @PathVariable int quantity) {
        try {
            customerService.purchaseCoupon(customerId, couponId, quantity); // Pass quantity to service
            return ResponseEntity.ok("Coupons purchased successfully");
        } catch (UnAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientCouponsQuantityException e) { // Custom exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough coupons available");
        }
    }

    @DeleteMapping("/{customerId}/remove/{couponId}")
    public ResponseEntity<String> removePurchasedCoupon(@PathVariable Long customerId, @PathVariable Long couponId) {
        try {
            customerService.removePurchasedCoupon(customerId, couponId);
            return ResponseEntity.ok("Coupon removed successfully.");
        } catch (UnAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerDetails(@PathVariable Long customerId) throws UnAuthorizedException {
        return customerService.getCustomerDetails(customerId);

    }
}
