package src.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import src.springboot.entities.Company;
import src.springboot.service.AdminService;
import src.springboot.utils.TokensManager;

import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // http://localhost:8080/api/admin/company
    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void addCompany(@RequestHeader("Authorization")@RequestBody Company company) throws Exception {
        adminService.addCompany(company);
    }


}
