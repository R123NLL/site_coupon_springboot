package src.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.impl.AdminServiceImpl;

import java.sql.SQLException;

public abstract class AdminTester {
    private static CompanyRepository companyRepository;

    private static CouponRepository couponRepository;

    private static CustomerRepository customerRepository;
    private static AdminServiceImpl adminServiceImpl = new AdminServiceImpl(companyRepository, customerRepository, couponRepository);
    private static final Logger logger = LoggerFactory.getLogger(AdminTester.class);

    public static void test() throws UnAuthorizedException, SQLException, InterruptedException {
        logger.info("******************* Admin Tester *******************");

        //login test
        logger.info("Admin Tester: testing login: " + adminServiceImpl.login("admin@admin.com", "admin"));

        //delete all test
        logger.info("Admin Tester: testing deleting all:\n");
        adminServiceImpl.deleteAll();
    }

}
