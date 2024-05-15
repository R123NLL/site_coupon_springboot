package src.springboot.service;

import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;

import java.sql.SQLException;

@Service
public abstract class ClientService {
    //    @Autowired
    protected final CompanyRepository companyRepository;
    //    @Autowired
    protected final CustomerRepository customerRepository;
    //    @Autowired
    protected final CouponRepository couponRepository;

    public ClientService(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;

}
