package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.springboot.dto.NewCouponRequest;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.Category;
import src.springboot.entities.ClientType;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.mapper.Mapper;
import src.springboot.service.CompanyService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController extends ClientController{
    @Autowired
    private CompanyService companyService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody NewLoginRequest newLoginRequest) {
        return super.login(newLoginRequest);
    }
    @GetMapping("/{companyId}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable Long companyId) throws UnAuthorizedException {
        return companyService.getCompanyCoupons(companyId);
    }

    @PostMapping("/{companyId}/coupons")
    public Coupon addCoupon(@PathVariable Long companyId, @RequestBody NewCouponRequest newCouponRequest) throws UnAuthorizedException {
        Coupon coupon = Mapper.mapToCoupon(newCouponRequest);
        coupon.setCompany(companyService.getCompanyDetails(companyId));
        return companyService.addCoupon(coupon);
    }

    @PutMapping("/{companyId}/coupons")
    public Coupon updateCoupon(@PathVariable Long companyId, @RequestBody NewCouponRequest newCouponRequest) throws UnAuthorizedException {
        Coupon coupon = Mapper.mapToCoupon(newCouponRequest);
        coupon.setCompany(companyService.getCompanyDetails(companyId));
        return companyService.updateCoupon(coupon);
    }

    @DeleteMapping("/{companyId}/coupons/{couponId}")
    public void deleteCoupon(@PathVariable Long companyId,@PathVariable Long couponId) throws UnAuthorizedException {
        companyService.deleteCoupon(companyId,couponId);
    }

    @GetMapping("/{companyId}/coupons/category/{category}")
    public List<Coupon> getCompanyCoupons(@PathVariable Long companyId, @PathVariable("category") Category category) throws UnAuthorizedException {
        return companyService.getCompanyCoupons(companyId, category);
    }

    @GetMapping("/{companyId}/coupons/price/{maxPrice}")
    public List<Coupon> getCompanyCoupons(@PathVariable Long companyId, @PathVariable double maxPrice) throws UnAuthorizedException {
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

}
