package src.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.repositories.CompanyRepository;
import src.springboot.repositories.CouponRepository;
import src.springboot.repositories.CustomerRepository;

import java.sql.SQLException;

@Service
public abstract class ClientService {
    @Autowired
    protected  CompanyRepository companyRepository;
    @Autowired
    protected  CustomerRepository customerRepository;
    @Autowired
    protected  CouponRepository couponRepository;

    public ClientService(){

    }

    public ClientService(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;

}
