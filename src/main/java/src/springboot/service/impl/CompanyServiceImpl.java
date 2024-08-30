package src.springboot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCouponRequest;
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
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    private Long companyId;

    public CompanyServiceImpl() {

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
    public Coupon addCoupon(Coupon coupon) throws UnAuthorizedException {
        notLoggedIn();

        Company company = companyRepository.findById(coupon.getCompany().getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        boolean couponExists = couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), company.getId());

        if (couponExists) {
            throw new IllegalArgumentException("Coupon with title " + coupon.getTitle() +
                    " for companyId: " + company.getId() + " already exists");
        }

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Coupon updatedCoupon) throws UnAuthorizedException {
        notLoggedIn();

        Coupon existingCoupon = couponRepository.findById(updatedCoupon.getId())
                .orElseThrow(() -> new EntityNotFoundException("Coupon not found"));

        Company company = companyRepository.findById(updatedCoupon.getCompany().getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        boolean couponExists = couponRepository.existsByTitleAndCompanyId(updatedCoupon.getTitle(), company.getId());
        if (couponExists && !existingCoupon.getTitle().equals(updatedCoupon.getTitle())) {
            throw new IllegalArgumentException("Coupon with title " + updatedCoupon.getTitle() +
                    " for companyId: " + company.getId() + " already exists");
        }

        existingCoupon.setCompany(company);
        existingCoupon.setTitle(updatedCoupon.getTitle());
        existingCoupon.setDescription(updatedCoupon.getDescription());
        existingCoupon.setCategory(updatedCoupon.getCategory());
        existingCoupon.setStartDate(updatedCoupon.getStartDate());
        existingCoupon.setEndDate(updatedCoupon.getEndDate());
        existingCoupon.setAmount(updatedCoupon.getAmount());
        existingCoupon.setPrice(updatedCoupon.getPrice());
        existingCoupon.setImage(updatedCoupon.getImage());


        return couponRepository.save(existingCoupon);
    }

    public void deleteCoupon(Long couponId) throws UnAuthorizedException {
        notLoggedIn();

        if (!couponRepository.existsById(couponId)) {
            throw new EntityNotFoundException("Coupon with ID " + couponId + " not found");
        }

        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId) {
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId, Category category) throws UnAuthorizedException {
        notLoggedIn();
        List<Coupon> coupons = couponRepository.findByCompanyIdAndCategory(companyId, category);

        if (coupons.isEmpty()) {
            System.out.println("No coupons found for companyId: " + companyId + " and category: " + category);
        }

        return coupons;
    }

    @Override
    public List<Coupon> getCompanyCoupons(Long companyId, double maxPrice) throws UnAuthorizedException {
        notLoggedIn();
        List<Coupon> coupons = couponRepository.findByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);

        if (coupons.isEmpty()) {
            System.out.println("No coupons found for companyId: " + companyId + " with max price of: " + maxPrice);
        }

        return coupons;
    }

    @Override
    public Company getCompanyDetails(Long companyID) throws UnAuthorizedException {
        return companyRepository.findById(companyID)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
    }

    // method to check authorization before using the other methods
    private void notLoggedIn() throws UnAuthorizedException {
        if (this.companyId <= 0) {
            throw new UnAuthorizedException("Access denied, please log in!");
        }
    }
}
