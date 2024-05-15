package src.springboot.service;

import src.springboot.entities.Company;
import src.springboot.entities.Coupon;

import java.util.ArrayList;

public interface CouponService {
    ArrayList<Coupon> getAllCoupons();

    void addCoupon();
}
