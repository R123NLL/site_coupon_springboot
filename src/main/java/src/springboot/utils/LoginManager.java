package src.springboot.utils;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.entities.ClientType;
import src.springboot.exceptions.LoginSecurityException;
import src.springboot.service.ClientService;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.service.impl.CompanyServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;

import java.util.Objects;
@Service
public class LoginManager {

    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;
    private final AdminServiceImpl adminService;
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    public LoginManager(CompanyServiceImpl companyService, CustomerServiceImpl customerService, AdminServiceImpl adminService, CustomerRepository customerRepository, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.adminService = adminService;
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
    }


    public ClientService login(String email, String password, ClientType type) throws LoginSecurityException {
        String INVALID_LOGIN = "Email or password is invalid, try again";
        return switch (type) {
            case Administrator -> {
                if (!Objects.equals(email, "admin@admin.com") && !Objects.equals(password, "admin")) {
                    throw new LoginSecurityException(INVALID_LOGIN);
                }
                yield adminService;
            }
            case Company -> {
                if (!companyRepository.isCompanyExists(email, password)) {
                    throw new LoginSecurityException(INVALID_LOGIN);
                }
                yield companyService;
            }
            case Customer -> {
                if (!customerRepository.existsCustomerByEmailAndPassword(email, password)) {
                    throw new LoginSecurityException(INVALID_LOGIN);
                }
                yield customerService;
            }
        };
    }
}
