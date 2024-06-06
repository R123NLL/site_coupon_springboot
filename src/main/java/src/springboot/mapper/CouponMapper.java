package src.springboot.mapper;

import src.springboot.dto.NewCouponRequest;
import src.springboot.entities.Coupon;

public class CouponMapper {
    public static Coupon mapToCoupon(NewCouponRequest ncr){
        Coupon coupon=new Coupon(ncr.getCompany(),ncr.getCategory(),ncr.getTitle(),
                ncr.getDescription(),ncr.getStartDate(),ncr.getEndDate(),
                ncr.getAmount(),ncr.getPrice(), ncr.getImage());
        return coupon;
    }
}
