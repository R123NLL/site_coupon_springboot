package src.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCouponRequest;
import src.springboot.dto.NewCustomerRequest;
import src.springboot.entities.Category;
import src.springboot.entities.Customer;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.mapper.CouponMapper;
import src.springboot.mapper.CustomerMapper;
import src.springboot.service.impl.CustomerServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;

public  abstract class CustomerTester {
    private static final Logger logger = LoggerFactory.getLogger(CustomerTester.class);
    private static CustomerRepository customerRepository;
    private static CustomerServiceImpl customerServiceimpl;
    private static CompanyRepository companyRepository;


    public static void test() throws UnAuthorizedException {
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

        //testing login
        logger.info("Customer Testing: login to customer:");

        //coupon testing for customer
        customerServiceimpl.purchaseCoupon(1,
                CouponMapper.mapToCoupon(new NewCouponRequest(companyRepository.getOneCompany(1), Category.getCategoryNameById(4),
                        "Outdoor Freezer", "Freezer for outdoor vacations",
                        LocalDate.of(2023, 12, 1), LocalDate.of(2024, 7, 1
                ), 50, 200, "D://Images/coupon6")));

        //show all companies
        logger.info("All the customers after testing: \n " + customerRepository.getAllCustomers());
    }

    private static void createAndSave5Customers() {
        customerRepository.saveAll(Arrays.asList(CustomerMapper.mapToCustomer(new NewCustomerRequest("David", "Levi", "DavidL@gmail.com", "0123012", null))
                , CustomerMapper.mapToCustomer(new NewCustomerRequest("Israel", "Israeli", "IsraelI@gmail.com", "21518754", null))
                , CustomerMapper.mapToCustomer(new NewCustomerRequest("Lior", "Pak", "LiorP@gmail.com", "8484151654", null))
                , CustomerMapper.mapToCustomer(new NewCustomerRequest("Yuval", "Levi", "YuvalL@gmail.com", "987894624", null))
                , CustomerMapper.mapToCustomer(new NewCustomerRequest("Rinat", "Cohen", "RinatC@gmail.com", "952318532", null))));
    }
}
