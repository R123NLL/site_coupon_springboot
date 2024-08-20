//package src.springboot.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.stereotype.Service;
//import src.springboot.dao.CompanyRepository;
//import src.springboot.dao.CustomerRepository;
//import src.springboot.entities.ClientType;
//import src.springboot.exceptions.LoginSecurityException;
//import src.springboot.service.ClientService;
////import src.springboot.service.impl.AdminServiceImpl;
////import src.springboot.service.impl.CompanyServiceImpl;
////import src.springboot.service.impl.CustomerServiceImpl;
//
//import java.sql.SQLException;
//import java.util.Objects;
//
//@Service
//public class LoginManager {
//
//    private final CompanyServiceImpl companyService;
//    private final CustomerServiceImpl customerService;
//    private final AdminServiceImpl adminService;
//
//    @Autowired
//    public LoginManager(CompanyServiceImpl companyService, CustomerServiceImpl customerService, AdminServiceImpl adminService) {
//        this.companyService = companyService;
//        this.customerService = customerService;
//        this.adminService = adminService;
//    }
//
//
//    public ClientService login(String email, String password, ClientType type) throws LoginSecurityException, SQLException, InterruptedException {
//        String INVALID_LOGIN = "Email or password is invalid, try again";
//        return switch (type) {
//            case Administrator -> {
//                if (!adminService.login(email,password)) {
//                    throw new LoginSecurityException(INVALID_LOGIN);
//                }
//                yield adminService;
//            }
//            case Company -> {
//                if (!companyService.login(email,password)) {
//                    throw new LoginSecurityException(INVALID_LOGIN);
//                }
//                yield companyService;
//            }
//            case Customer -> {
//                if (!customerService.login(email,password)) {
//                    throw new LoginSecurityException(INVALID_LOGIN);
//                }
//                yield customerService;
//            }
//        };
//    }
//}
