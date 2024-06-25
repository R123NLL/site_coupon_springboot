package src.springboot.test;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCompanyRequest;
import src.springboot.dto.NewCouponRequest;
import src.springboot.entities.*;
import src.springboot.exceptions.LoginSecurityException;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.mapper.CompanyMapper;
import src.springboot.mapper.CouponMapper;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.service.impl.CompanyServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;
import src.springboot.utils.LoginManager;

import java.sql.SQLException;
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
    private CompanyServiceImpl companyServiceimpl;
    @Autowired
    private CustomerServiceImpl customerServiceimpl;
    @Autowired
    private AdminServiceImpl adminServiceimpl;
    @Autowired
    private LoginManager loginManager;
    private static Test instance;


    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private Test() {

    }

    public static synchronized Test getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    public void showcase() throws UnAuthorizedException, SQLException, InterruptedException {
        logger.info("");
        logger.info("");
        logger.info("Starting Tests");
        logger.info("");
        logger.info("");
        AdminTester.test();
        CouponTester.test();
        //CustomerTester.test();
        //todo CompanyTester.test();
    }
}
