package src.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.springboot.entities.ClientType;

@RestController
@RequestMapping("/company")
public class CompanyController extends ClientController{
    @Override
    protected ClientType getClientType() {
        return ClientType.Company;
    }
}
