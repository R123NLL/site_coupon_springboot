package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CouponRepository couponRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);

    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return companyRepository.isCompanyExists(email,password);
    }

    @Override
    public void addCoupon(Coupon coupon, int companyId) throws UnAuthorizedException {

        coupon.setCompanyID(companyId);
        if (!couponRepository.isCouponExist(coupon)) {
            couponRepository.save(coupon);
        } else {
            System.out.println("Coupon already with title " + coupon.getTitle() + " for companyId:" + companyId + " exist");
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) throws UnAuthorizedException {

        couponRepository.save(coupon);

    }


    public void deleteCoupon(Long couponId) throws UnAuthorizedException {

        couponRepository.deleteById(couponId);

    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws UnAuthorizedException {

        return couponRepository.getAllCompanyCoupons(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws UnAuthorizedException {

        return couponRepository.getAllCompanyCoupons(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws UnAuthorizedException {

        return couponRepository.getCompanyCouponsBelowPrice(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws UnAuthorizedException {

        return companyRepository.getOneCompany(companyId);
    }


}
