//package src.springboot.test;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import src.springboot.dao.CompanyRepository;
//import src.springboot.dao.CouponRepository;
//import src.springboot.dao.CustomerRepository;
//import src.springboot.dto.NewCouponRequest;
//import src.springboot.entities.Category;
//import src.springboot.exceptions.UnAuthorizedException;
//import src.springboot.mapper.Mapper;
//import src.springboot.service.impl.AdminServiceImpl;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class CouponTester {
//    private final AdminServiceImpl adminService;
//    private final CouponRepository couponRepository;
//    private final CompanyRepository companyRepository;
//    private final Logger logger = LoggerFactory.getLogger(CouponTester.class);
//
//    @Autowired
//    public CouponTester(CompanyRepository companyRepository,
//                        CustomerRepository customerRepository,
//                        CouponRepository couponRepository) {
//        this.adminService = new AdminServiceImpl(companyRepository, customerRepository, couponRepository);
//        this.couponRepository = couponRepository;
//        this.companyRepository = companyRepository;
//    }
//
//    public void test() throws UnAuthorizedException, SQLException, InterruptedException {
//        logger.info("******************* Coupon Tester *******************");
//
//        // creating 5 coupons
//        logger.info("------ Creating Coupons coupons1-coupons5 ------");
//        createAndSave5Coupons();
//
//        //show all coupons
//        logger.info("All the coupons before testing:\n " + couponRepository.findAll());
//
//        //isExists
//        logger.info("Coupon Testing: is coupon exists? (coleman Water Bottle -true) " +
//                couponRepository.existsCouponByTitle("Water Bottle"));
//        logger.info("Coupon Testing: is coupon exists? (Barbie Doll-false) " +
//                couponRepository.existsCouponByTitle("Barbie Doll"));
//
//        //deleting and checking
//        logger.info("------ Coupon Testing: deleting non existing coupon ------");
//        logger.info(("is coupon with id 10 exists?: " + couponRepository.existsById(10L)));
//        logger.info("Deleting coupon with id 10");
//        couponRepository.deleteById(10L);
//        logger.info("Coupon Testing: checking if coupon is deleted: ");
//        logger.info("" + couponRepository.getOneCoupon(10));
//
//        logger.info("------ Coupon Testing: deleting existing coupon ------");
//        logger.info(("is coupon with id 5 exists?:" + couponRepository.existsById(5L)));
//        logger.info("Deleting coupon with id 5");
//        couponRepository.deleteById(5L);
//        logger.info("Coupon Testing: checking if coupon is deleted");
//        logger.info("" + couponRepository.getOneCoupon(5));
//
//        //adding and checking
//        logger.info("Coupon Testing: adding new Coupon (Freezer) ");
//        couponRepository.save(Mapper.mapToCoupon(
//                new NewCouponRequest(companyRepository.getOneCompany(1), Category.getCategoryNameById(4), "Outdoor Freezer", "Freezer for outdoor vacations",
//                        LocalDate.of(2023, 12, 1), LocalDate.of(2024, 7, 1), 50, 200,
//                        "D://Images/coupon6")));
//        logger.info("" + couponRepository.getOneCoupon(6));
//
//        //show all coupons
//        logger.info("All the Coupons after testing: \n " + couponRepository.findAllCoupons());
//    }
//
//    private void createAndSave5Coupons() {
//        couponRepository.saveAll(List.of(Mapper.mapToCoupon(
//                        new NewCouponRequest(companyRepository.getOneCompany(1), Category.getCategoryNameById(4), "Water Bottle", "Water bottle for outdoor vacations",
//                                LocalDate.of(2023, 12, 1), LocalDate.of(2024, 7, 1), 50, 20,
//                                "D://Images/coupon1"))
//                , Mapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(2), Category.getCategoryNameById(2), "Pc monitor", "Monitor screen for PC",
//                        LocalDate.of(2023, 12, 31), LocalDate.of(2024, 5, 1), 30, 50,
//                        "D://Images/coupon2"))
//                , Mapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(3), Category.getCategoryNameById(2), "Headset", "Headset for gaming",
//                        LocalDate.of(2024, 5, 1), LocalDate.of(2024, 7, 1), 50, 20,
//                        "D://Images/coupon3"))
//                , Mapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(4), Category.getCategoryNameById(1), "Dictionary", "The food for brain",
//                        LocalDate.of(2023, 12, 1), LocalDate.of(2024, 7, 1), 100, 80,
//                        "D://Images/coupon4"))
//                , Mapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(5), Category.getCategoryNameById(2), "Wireless Speaker", "Bluetooth speaker",
//                        LocalDate.of(2023, 1, 1), LocalDate.of(2023, 7, 1), 300, 60,
//                        "D://Images/coupon5"))));
//        logger.info("Created 5 coupons");
//    }
//}
