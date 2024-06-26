package src.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.exceptions.UnAuthorizedException;

import java.sql.SQLException;

@Service
public class Test {
    @Autowired
    AdminTester adminTester;
    @Autowired
    CustomerTester customerTester;
    @Autowired
    CouponTester couponTester;

    @Autowired
    CompanyTester companyTester;
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private Test() {

    }

    public void showcase() throws UnAuthorizedException, SQLException, InterruptedException {
        logger.info("\n\n~~~Starting Tests~~~\n\n");
        adminTester.test();
        couponTester.test();
        customerTester.test();
        companyTester.test();
        logger.info("\n\n~~~Finished Tests~~~\n\n");
    }
}
