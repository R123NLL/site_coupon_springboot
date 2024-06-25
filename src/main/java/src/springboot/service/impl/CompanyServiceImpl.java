package src.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCompanyRequest;
import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.job.CouponExpirationDailyJob;
import src.springboot.service.ClientService;
import src.springboot.service.CompanyService;

import java.sql.SQLException;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CouponRepository couponRepository;
    private int companyId;

    private static final Logger logger = LoggerFactory.getLogger(CouponExpirationDailyJob.class);


    public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);

    }

    @Override
    public boolean login(String email, String password) {
        boolean isNotNull = nonNull(email) && nonNull(password);
        boolean isNotEmpty = !(email.isEmpty()) && !(password.isEmpty());

        if (isNotNull && isNotEmpty) {

            Company companyByEmail = companyRepository.getOneCompanyByEmail(email);
            if (nonNull(companyByEmail)) {
                companyId = companyByEmail.getId();
                return companyByEmail.getPassword().equals(password);
            }
        }
        return false;
    }

    @Override
    public void addCoupon(Coupon coupon, int companyId) throws UnAuthorizedException {
        notLoggedIn();

        //Fetching the company by its ID
        Company company = companyRepository.getOneCompany(companyId);
        coupon.setCompany(company);

        if (!couponRepository.isCouponExist(coupon)) {
            couponRepository.save(coupon);
        } else {
            System.out.println("Coupon already with title " + coupon.getTitle() + " for companyId:" + companyId + " exist");
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();
        couponRepository.save(coupon);
    }


    public void deleteCoupon(Long couponId) throws UnAuthorizedException {
        notLoggedIn();
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCompanyCoupons(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCompanyCoupons(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getCompanyCouponsBelowPrice(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws UnAuthorizedException {
        notLoggedIn();
        return companyRepository.getOneCompany(companyId);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (this.companyId <= 0) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}
