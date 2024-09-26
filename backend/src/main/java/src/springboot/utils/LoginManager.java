package src.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.entities.ClientType;
import src.springboot.exceptions.LoginSecurityException;
import src.springboot.exceptions.UnAuthorizedException;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.service.impl.CompanyServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;


import java.sql.SQLException;

import static src.springboot.entities.ClientType.*;

@Service
public class LoginManager {
    @Autowired
    private JwtUtil jwtUtil;

    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;
    private final AdminServiceImpl adminService;


    @Autowired
    public LoginManager(CompanyServiceImpl companyService, CustomerServiceImpl customerService, AdminServiceImpl adminService) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.adminService = adminService;
    }


    public String login(String email, String password, ClientType type) throws LoginSecurityException, SQLException, InterruptedException {
        Long userId;
        String role;

        switch (type) {
            case Administrator:
                if (!adminService.login(email, password)) {
                    throw new LoginSecurityException("Email or password is invalid, try again");
                }
                userId = adminService.getAdminId();
                role = Administrator.toString();
                break;

            case Company:
                if (!companyService.login(email, password)) {
                    throw new LoginSecurityException("Email or password is invalid, try again");
                }
                userId = companyService.getIdByEmail(email);
                role = Company.toString();
                break;

            case Customer:
                if (!customerService.login(email, password)) {
                    throw new LoginSecurityException("Email or password is invalid, try again");
                }
                userId = customerService.getIdByEmail(email);
                role = Customer.toString();
                break;

            default:
                throw new IllegalArgumentException("Unknown client type");
        }

        return jwtUtil.generateToken(userId, role); // Return token upon successful login
    }
}
