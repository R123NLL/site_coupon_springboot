package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CouponRepository;
import src.springboot.entities.Coupon;
import src.springboot.service.CouponService;

import java.util.ArrayList;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public ArrayList<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public void addCoupon() {

    }
}
