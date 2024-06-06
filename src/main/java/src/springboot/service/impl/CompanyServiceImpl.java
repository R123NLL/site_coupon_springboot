package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.ClientService;
import src.springboot.service.CompanyService;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    private final int companyId;

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository, int companyId) {
        super(companyRepository, customerRepository, couponRepository);
        this.companyId = companyId;
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return couponRepository.existsCompanyByEmailAndPassword(email, password);
    }

    @Override
    public void addCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();
        coupon.setCompanyID(companyId);
        if (!couponRepository.isCouponExist(coupon)) {
            couponRepository.addCoupon(coupon);
        } else {
            System.out.println("Coupon already with title " + coupon.getTitle() + " for companyId:" + companyId + " exist");
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();
        couponRepository.updateCoupon(coupon);

    }

    @Override
    public void deleteCoupon(int couponId) throws UnAuthorizedException {
        notLoggedIn();
        couponRepository.deleteCoupon(couponId);

    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons() throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCompanyCoupons(companyId);
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(Category category) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCompanyCoupons(companyId, category);
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getCompanyCouponsBelowPrice(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails() throws UnAuthorizedException {
        notLoggedIn();
        return companyRepository.getOneCompany(companyId);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (this.companyId <= 0) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}
