package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.CompanyService;

import java.util.ArrayList;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public void addCoupon(Coupon coupon) throws UnAuthorizedException {

    }

    @Override
    public void updateCoupon(Coupon coupon) throws UnAuthorizedException {

    }

    @Override
    public void deleteCoupon(int couponId) throws UnAuthorizedException {

    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons() throws UnAuthorizedException {
        return null;
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(Category category) throws UnAuthorizedException {
        return null;
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws UnAuthorizedException {
        return null;
    }

    @Override
    public Company getCompanyDetails() throws UnAuthorizedException {
        return null;
    }
}
