package src.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.entities.ClientType;
import src.springboot.exceptions.LoginSecurityException;
import src.springboot.service.ClientService;
import src.springboot.service.impl.AdminServiceImpl;
import src.springboot.service.impl.CompanyServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;


import java.sql.SQLException;

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
        ClientService clientService;

        switch (type) {
            case Administrator:
                if (!adminService.login(email, password)) {
                    throw new LoginSecurityException("Email or password is invalid, try again");
                }
                clientService = adminService;
                break;

            case Company:
                if (!companyService.login(email, password)) {
                    throw new LoginSecurityException("Email or password is invalid, try again");
                }
                clientService = companyService;
                break;

            case Customer:
                if (!customerService.login(email, password)) {
                    throw new LoginSecurityException("Email or password is invalid, try again");
                }
                clientService = customerService;
                break;

            default:
                throw new IllegalArgumentException("Unknown client type");
        }

        return jwtUtil.generateToken(email); // Return token upon successful login
    }
}
