package src.springboot.strategy.impl;

import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.ClientType;
import src.springboot.service.ClientService;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.service.impl.CompanyServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;

import java.sql.SQLException;

public class DefaultLoginStrategy {
    private static CompanyRepository companyRepository;
    private static CustomerRepository customerRepository;
    private static CouponRepository couponRepository;


    public static ClientService getFacadeByLogin(String email, String password, ClientType clientType) throws SQLException, InterruptedException {
        ClientService result = switch (clientType) {
            case Company -> new CompanyServiceImpl(companyRepository,customerRepository,couponRepository);
            case Customer -> new CustomerServiceImpl(companyRepository,customerRepository,couponRepository);
            case Administrator -> new AdminServiceImpl(companyRepository,customerRepository,couponRepository);
        };
        return result.login(email, password) ? result : null;
    }
}
