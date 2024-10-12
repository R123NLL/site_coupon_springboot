package src.springboot.service.impl;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.exceptions.InsufficientCouponsQuantityException;
import src.springboot.repositories.CompanyRepository;
import src.springboot.repositories.CouponRepository;
import src.springboot.repositories.CustomerRepository;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.ClientService;
import src.springboot.service.CustomerService;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.util.Objects.nonNull;


@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    private Long customerID;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

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
                logger.info("Logged is successfully, Welcome back " + customerByEmail.getFirstName());
                return customerByEmail.getPassword().equals(password);
            }
        }
        logger.error("Login failed: Email or password are incorrect, try again");
        return false;
    }

    public Long getIdByEmail(String email) {
        return customerRepository.findByEmail(email).getId();
    }

    @Override
    @Transactional
    public void purchaseCoupon(Long customerId, Long couponId) throws UnAuthorizedException {
        notLoggedIn(customerId);

        // Check if the customer exists
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));

        // Check if the coupon exists
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found with ID: " + couponId));

        // check if customer own this coupon
        boolean isAlreadyPurchased = customer.getCoupons().stream().anyMatch(c -> Objects.equals(c.getId(), coupon.getId()));

        // Check if enough coupons are available for purchase
        if (coupon.getAmount() < 1 || isAlreadyPurchased) {
            throw new InsufficientCouponsQuantityException("Customer own this coupon or not enough coupons available for coupon ID: " + couponId);
        }

        // Deduct the purchased quantity from the coupon's available amount
        coupon.setAmount(coupon.getAmount() - 1);

        try {
            // Save the updated coupon back to the repository
            couponRepository.save(coupon);

            //add the coupon to the customer and save the updated customer
            customer.getCoupons().add(coupon);
            customerRepository.save(customer);

        } catch (Exception e) {
            throw new InsufficientCouponsQuantityException("The purchase of the coupon failed due to a change at the same time: " + e.getMessage());
        }
    }


    @Override
    @Transactional
    public void removePurchasedCoupon(Long customerId, Long couponId) throws UnAuthorizedException {
        notLoggedIn(customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));

        if (!customer.getCoupons().remove(coupon)) {
            throw new IllegalArgumentException("Coupon not purchased by customer.");
        }

        try {
            customerRepository.save(customer);
            coupon.setAmount(coupon.getAmount()+1);
            couponRepository.save(coupon);
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public List<Coupon> getCustomerCoupons(Long customerId) throws UnAuthorizedException {
        notLoggedIn(customerId);
        return couponRepository.findByCustomersId(customerId);

    }

    @Override
    public List<Coupon> getCustomerCoupons(Long customerId, Category category) throws UnAuthorizedException {
        notLoggedIn(customerId);
        return couponRepository.findByCustomersIdAndCategory(customerId, category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Long customerId, double maxPrice) throws UnAuthorizedException {
        notLoggedIn(customerId);
        return couponRepository.findByCustomersIdAndPriceLessThanEqual(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(Long customerId) throws UnAuthorizedException {
        notLoggedIn(customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    private void notLoggedIn(Long id) throws UnAuthorizedException {
        if (!Objects.equals(customerID, id)) {
            throw new UnAuthorizedException("Access denied, please log in first!");
        }
    }
}
