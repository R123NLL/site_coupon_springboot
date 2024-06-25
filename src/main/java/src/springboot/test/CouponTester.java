package src.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dto.NewCouponRequest;
import src.springboot.entities.Category;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.mapper.CouponMapper;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;

import java.time.LocalDate;
import java.util.List;

public  abstract class CouponTester {
    private static CompanyRepository companyRepository;
    private static CouponRepository couponRepository;
    private static AdminServiceImpl adminServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(CouponTester.class);

    public static void test() throws UnAuthorizedException {
        logger.info("******************* Coupon Tester *******************");

        // creating 5 coupons
        logger.info("------ Creating Coupons coupons1-coupons5 ------");
        createAndSave5Coupons();

        //show all coupons
        logger.info("All the coupons before testing:\n " + adminServiceImpl.getAllCoupons());

        //isExists
        logger.info("Coupon Testing: is coupon exists? (coleman Water Bottle -true) " +
                couponRepository.existsCouponByTitle("Water Bottle"));
        logger.info("Coupon Testing: is coupon exists? (Barbie Doll-false) " +
                couponRepository.existsCouponByTitle("Barbie Doll"));

        //deleting and checking
        logger.info("------ Coupon Testing: deleting non existing coupon ------");
        logger.info(("is coupon with id 10 exists?: " + couponRepository.existsById(10L)));
        logger.info("Deleting coupon with id 10");
        couponRepository.deleteById(10L);
        logger.info("Coupon Testing: checking if coupon is deleted: ");
        logger.info("" + couponRepository.getOneCoupon(10));

        logger.info("------ Coupon Testing: deleting existing coupon ------");
        logger.info(("is coupon with id 5 exists?:" + couponRepository.existsById(5L)));
        logger.info("Deleting coupon with id 5");
        couponRepository.deleteById(5L);
        logger.info("Customer Testing: checking if coupon is deleted");
        logger.info("" + couponRepository.getOneCoupon(5));

        //adding and checking
        logger.info("Coupon Testing: adding new Coupon (Freezer) ");
        Coupon coupon6 = new Coupon();
        coupon6.setCompany(companyRepository.getOneCompany(1));
        coupon6.setCategory(Category.getCategoryNameById(4));
        coupon6.setTitle("Outdoor Freezer");
        coupon6.setDescription("Freezer for outdoor vacations");
        coupon6.setStartDate(LocalDate.of(2023, 12, 1));
        coupon6.setEndDate(LocalDate.of(2024, 7, 1));
        coupon6.setAmount(50);
        coupon6.setPrice(200);
        coupon6.setImage("D://Images/coupon6");
        couponRepository.save(coupon6);
        logger.info("" + couponRepository.getOneCoupon(6));

        //show all coupons
        logger.info("All the Coupons after testing: \n " + couponRepository.findAllCoupons());
    }

    private static void createAndSave5Coupons() {
        couponRepository.saveAll(List.of(CouponMapper.mapToCoupon(
                        new NewCouponRequest(companyRepository.getOneCompany(1), Category.getCategoryNameById(4), "Water Bottle", "Water bottle for outdoor vacations",
                                LocalDate.of(2023, 12, 1), LocalDate.of(2024, 7, 1), 50, 20,
                                "D://Images/coupon1"))
                , CouponMapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(2), Category.getCategoryNameById(2), "Pc monitor", "Monitor screen for PC",
                        LocalDate.of(2023, 12, 31), LocalDate.of(2024, 5, 1), 30, 50,
                        "D://Images/coupon2"))
                , CouponMapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(3), Category.getCategoryNameById(2), "Headset", "Headset for gaming",
                        LocalDate.of(2024, 5, 1), LocalDate.of(2024, 7, 1), 50, 20,
                        "D://Images/coupon3"))
                , CouponMapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(4), Category.getCategoryNameById(1), "Dictionary", "The food for brain",
                        LocalDate.of(2023, 12, 1), LocalDate.of(2024, 7, 1), 100, 80,
                        "D://Images/coupon4"))
                , CouponMapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(5), Category.getCategoryNameById(2), "Wireless Speaker", "Bluetooth speaker",
                        LocalDate.of(2023, 1, 1), LocalDate.of(2023, 7, 1), 300, 60,
                        "D://Images/coupon5"))));
    }
}
