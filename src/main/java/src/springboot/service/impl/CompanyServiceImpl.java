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


import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class CompanyServiceImpl extends ClientService  implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    private  Long companyId;

    public CompanyServiceImpl(){

    }

    public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);

    }

    @Override
    public boolean login(String email, String password) {
        boolean isNotNull = nonNull(email) && nonNull(password);
        boolean isNotEmpty = !(email.isEmpty()) && !(password.isEmpty());

        if (isNotNull && isNotEmpty) {

            Company companyByEmail = companyRepository.findByEmail(email);
            if (nonNull(companyByEmail)) {
                companyId = companyByEmail.getId();
                return companyByEmail.getPassword().equals(password);
            }
        }
        return false;
    }

    @Override
    public List<Coupon> getAllCoupons(Long companyId) {
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public Coupon addCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();

        //Fetching the company by its ID
        Optional<Company> company = Optional.ofNullable(companyRepository.findById(coupon.getCompany().getId()).orElse(null));
        newCouponRequest.setCompany(company);

        if (!) {
            couponRepository.save(coupon);
        } else {
            System.out.println("Coupon already with title " + coupon.getTitle() + " for companyId:" + companyId + " exist");
        }
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();
        couponRepository.save(coupon);
    }


    public void deleteCoupon(Long couponId) throws UnAuthorizedException {
        notLoggedIn();
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCompanyCoupons(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId, Category category) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getAllCompanyCoupons(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId, double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        return couponRepository.getCompanyCouponsBelowPrice(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(Long companyId) throws UnAuthorizedException {
        notLoggedIn();
        return companyRepository.findById(companyId);
    }

    private void notLoggedIn() throws UnAuthorizedException {
        if (this.companyId <= 0) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}
