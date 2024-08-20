package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.springboot.dto.NewCouponRequest;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.mapper.Mapper;
import src.springboot.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Coupon> getAllCoupons(Long companyId){
        return companyService.getAllCoupons(companyId);
    }
    @PostMapping
    public Coupon addCoupon(@RequestBody NewCouponRequest newCouponRequest) throws UnAuthorizedException {
        return companyService.addCoupon(Mapper.mapToCoupon(newCouponRequest));
    }

}
