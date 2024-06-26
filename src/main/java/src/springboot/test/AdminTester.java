package src.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.ClientType;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.ClientService;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.strategy.impl.DefaultLoginStrategy;
import src.springboot.utils.LoginManager;

import java.sql.SQLException;
@Service
public class AdminTester {
    private final AdminServiceImpl adminService;

    private final Logger logger = LoggerFactory.getLogger(AdminTester.class);


    @Autowired
    public AdminTester(CompanyRepository companyRepository,
                       CustomerRepository customerRepository,
                       CouponRepository couponRepository) {
        this.adminService = new AdminServiceImpl(companyRepository, customerRepository, couponRepository);
    }
    public void test() throws UnAuthorizedException, SQLException, InterruptedException {
        logger.info("******************* Admin Tester *******************");

        //login test
        logger.info("Admin Tester: testing login: " + adminService.login("admin@admin.com","admin"));

        //delete all test
        logger.info("Admin Tester: testing deleting all:");
        adminService.deleteAll();
    }

}
