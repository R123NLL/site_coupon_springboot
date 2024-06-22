package src.springboot.test;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.Category;
import src.springboot.entities.Company;
import src.springboot.entities.Coupon;
import src.springboot.entities.Customer;
import src.springboot.job.CouponExpirationDailyJob;
import src.springboot.service.CompanyService;
import src.springboot.service.CustomerService;
import src.springboot.utils.LoginManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class Test {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginManager loginManager;
    private static Test instance;


    private static final Logger logger = LoggerFactory.getLogger(CouponExpirationDailyJob.class);

    private Test() {

    }

    public static synchronized Test getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    public void showcase() {
        logger.info("");
        logger.info("");
        logger.info("Starting Tests");
        logger.info("");
        logger.info("");
        deleteAll();
        couponTester();
        customerTester();
        companyTester();
    }

    private void couponTester() {
        logger.info("******************* Coupon Tester *******************");

        // creating 5 coupons
        logger.info("------ Creating Coupons coupons1-coupons5 ------");
        createAndSave5Coupons();

        //show all coupons
        logger.info("All the coupons before testing:\n " + couponRepository.findAllCoupons());

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
        coupon6.setCompanyID(1);
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

    private void companyTester() {
        logger.info("******************* Company Tester *******************");

        // creating 5 companies
        logger.info("------ Creating Companies c1-c5 ------");
        createAndSave5Companies();

        //show all companies
        logger.info("All the companies before testing: \n " + companyRepository.getAllCompanies());

        //isExists
        logger.info("Company Testing: is company exists? (coleman-true) " +
                companyRepository.isCompanyExists("office@Coleman.com", "12345"));
        logger.info("Company Testing: is company exists? (google-false) " +
                companyRepository.isCompanyExists("office@google.com", "12345"));

        //deleting and checking
        logger.info("------ Company Testing: deleting non existing company ------");
        logger.info(("is company with id 10 exists? " + companyRepository.existsById(10L)));
        logger.info("Deleting company with id 10");
        companyRepository.deleteCompany(10);
        logger.info("Company Testing: checking if company is deleted");
        logger.info("" + companyRepository.getOneCompany(10));

        logger.info("------ Company Testing: deleting existing company ------");
        logger.info(("is company with id 5 exists? " + companyRepository.existsById(5L)));
        logger.info("Deleting company with id 5");
        companyRepository.deleteCompany(5);
        logger.info("Company Testing: checking if company is deleted");
        logger.info("" + companyRepository.getOneCompany(5));

        //adding and checking
        logger.info("Company Testing: adding new company (El-AL) ");
        companyRepository.addCompany(new Company("El-Al", "office@elal.co.il", "0123456", null));
        logger.info("" + companyRepository.getOneCompanyByEmail("office@elal.co.il"));

        //show all companies
        logger.info("All the companies after testing: \n " + companyRepository.getAllCompanies());

        logger.info("show the ");
        companyService.
    }

    private void customerTester() {
        logger.info("******************* Customer Tester *******************");
        logger.info("------ Creating Customers customer1-customer5 ------");
        createAndSave5Customers();

        //show all customers
        logger.info("All the customers before testing: \n " + customerRepository.getAllCustomers());

        //isExists
        logger.info("Customer Testing: is customer exists? (David-true) " +
                customerRepository.isCustomerExists("DavidL@gmail.com"));
        logger.info("Customer Testing: is customer exists? (Ronen-false) " +
                customerRepository.isCustomerExists("RonenN@gmail.com"));
        //deleting and checking
        logger.info("------ Customer Testing: deleting non existing customer ------");
        logger.info(("is customer with id 10 exists? " + customerRepository.existsById(10L)));
        logger.info("Deleting customer with id 10");
        customerRepository.deleteCustomer(10);
        logger.info("Customer Testing: checking if customer is deleted");
        logger.info("" + customerRepository.getOneCustomer(10));

        logger.info("------ Customer Testing: deleting existing customer ------");
        logger.info(("is customer with id 5 exists? " + customerRepository.existsById(5L)));
        logger.info("Deleting customer with id 5");
        customerRepository.deleteCustomer(5);
        logger.info("Customer Testing: checking if company is deleted");
        logger.info("" + customerRepository.getOneCustomer(5));


        //adding and checking
        logger.info("Customer Testing: adding new customer (Roy) ");
        customerRepository.save(new Customer("Roy", "Yaakov", "RoyY@gmail.com", "123456", null));
        logger.info("" + customerRepository.getOneCustomerByEmail("RoyY@gmail.com"));

        //show all companies
        logger.info("All the customers after testing: \n " + customerRepository.getAllCustomers());
    }

    private void createAndSave5Companies() {
        Company c1 = new Company();
        c1.setName("Coleman");
        c1.setEmail("office@Coleman.com");
        c1.setPassword("12345");
        c1.setCoupons(couponRepository.getAllCompanyCoupons(1, Category.getCategoryNameById(4)));

        Company c2 = new Company();
        c2.setName("ViewSonic");
        c2.setEmail("office@Viewsonic.com");
        c2.setPassword("123123");
        c2.setCoupons(couponRepository.getAllCompanyCoupons(2, Category.getCategoryNameById(2)));

        Company c3 = new Company();
        c3.setName("Logitech");
        c3.setEmail("office@Logitech.com");
        c3.setPassword("54321");
        c3.setCoupons(couponRepository.getAllCompanyCoupons(3, Category.getCategoryNameById(2)));

        Company c4 = new Company();
        c4.setName("Oxford");
        c4.setEmail("office@Oxford.com");
        c4.setPassword("122333");
        c4.setCoupons(couponRepository.getAllCompanyCoupons(4, Category.getCategoryNameById(1)));

        Company c5 = new Company();
        c5.setName("Jbl");
        c5.setEmail("office@Jbl.com");
        c5.setPassword("132213");
        c5.setCoupons(couponRepository.getAllCompanyCoupons(5, Category.getCategoryNameById(2)));

        companyRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
    }

    private void createAndSave5Customers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("David");
        customer1.setLastName("Levi");
        customer1.setEmail("DavidL@gmail.com");
        customer1.setPassword("0123012");
        Customer customer2 = new Customer();
        customer2.setFirstName("Israel");
        customer2.setLastName("Israeli");
        customer2.setEmail("IsraelI@gmail.com");
        customer2.setPassword("21518754");
        Customer customer3 = new Customer();
        customer3.setFirstName("Lior");
        customer3.setLastName("Pak");
        customer3.setEmail("LiorP@gmail.com");
        customer3.setPassword("8484151654");
        Customer customer4 = new Customer();
        customer4.setFirstName("Yuval");
        customer4.setLastName("Levi");
        customer4.setEmail("YuvalL@gmail.com");
        customer4.setPassword("987894624");
        Customer customer5 = new Customer();
        customer5.setFirstName("Rinat");
        customer5.setLastName("Cohen");
        customer5.setEmail("RinatC@gmail.com");
        customer5.setPassword("952318532");
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5));
    }

    private void createAndSave5Coupons() {
        Coupon coupon1 = new Coupon();
        coupon1.setCompanyID(1);
        coupon1.setCompany(companyRepository.getOneCompany(1));
        coupon1.setCategory(Category.getCategoryNameById(4));
        coupon1.setTitle("Water Bottle");
        coupon1.setDescription("Water bottle for outdoor vacations");
        coupon1.setStartDate(LocalDate.of(2023, 12, 1));
        coupon1.setEndDate(LocalDate.of(2024, 7, 1));
        coupon1.setAmount(50);
        coupon1.setPrice(20);
        coupon1.setImage("D://Images/coupon1");

        Coupon coupon2 = new Coupon();
        coupon2.setCompanyID(2);
        coupon2.setCompany(companyRepository.getOneCompany(2));
        coupon2.setCategory(Category.getCategoryNameById(2));
        coupon2.setTitle("Pc monitor");
        coupon2.setDescription("Monitor screen for PC");
        coupon2.setStartDate(LocalDate.of(2023, 12, 31));
        coupon2.setEndDate(LocalDate.of(2024, 5, 1));
        coupon2.setAmount(10);
        coupon2.setPrice(200);
        coupon2.setImage("D://Images/coupon2");

        Coupon coupon3 = new Coupon();
        coupon3.setCompanyID(3);
        coupon3.setCompany(companyRepository.getOneCompany(3));
        coupon3.setCategory(Category.getCategoryNameById(2));
        coupon3.setTitle("Headset");
        coupon3.setDescription("Headset for gaming");
        coupon3.setStartDate(LocalDate.of(2024, 5, 1));
        coupon3.setEndDate(LocalDate.of(2024, 7, 1));
        coupon3.setAmount(30);
        coupon3.setPrice(50);
        coupon3.setImage("D://Images/coupon3");

        Coupon coupon4 = new Coupon();
        coupon4.setCompanyID(4);
        coupon4.setCompany(companyRepository.getOneCompany(4));
        coupon4.setCategory(Category.getCategoryNameById(1));
        coupon4.setTitle("Dictionary");
        coupon4.setDescription("The food for brain");
        coupon4.setStartDate(LocalDate.of(2024, 1, 1));
        coupon4.setEndDate(LocalDate.of(2024, 6, 1));
        coupon4.setAmount(100);
        coupon4.setPrice(80);
        coupon4.setImage("D://Images/coupon4");

        Coupon coupon5 = new Coupon();
        coupon5.setCompanyID(5);
        coupon5.setCompany(companyRepository.getOneCompany(5));
        coupon5.setCategory(Category.getCategoryNameById(2));
        coupon5.setTitle("Wireless Speaker");
        coupon5.setDescription("Bluetooth speaker");
        coupon5.setStartDate(LocalDate.of(2023, 1, 1));
        coupon5.setEndDate(LocalDate.of(2023, 7, 1));
        coupon5.setAmount(300);
        coupon5.setPrice(60);
        coupon5.setImage("D://Images/coupon5");
        couponRepository.saveAll(List.of(coupon1, coupon2, coupon3, coupon4, coupon5));
    }

    private void deleteAll() {
        couponRepository.deleteAll();
        customerRepository.deleteAll();
        companyRepository.deleteAll();
    }
}
