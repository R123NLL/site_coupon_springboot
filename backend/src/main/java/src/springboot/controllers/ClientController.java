package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.Coupon;
import src.springboot.exceptions.LoginSecurityException;
import src.springboot.service.AdminService;
import src.springboot.service.impl.CustomerServiceImpl;
import src.springboot.utils.LoginManager;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController {
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public ResponseEntity<String> showLoginPage() {
        // Return a simple HTML or text response
        return ResponseEntity.ok("Please POST your login credentials to /login.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody NewLoginRequest newLoginRequest) {
        try {
            // Call login from LoginManager and get the JWT token
            String token = loginManager.login(newLoginRequest.getEmail(), newLoginRequest.getPassword(), newLoginRequest.getClientType());
            return ResponseEntity.ok(token);
        } catch (LoginSecurityException | SQLException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    @GetMapping("/coupons/active")
    public ResponseEntity<List<Coupon>> getAllActiveCoupons() {
        List<Coupon> activeCoupons = adminService.getAllActiveCoupons();
        return ResponseEntity.ok(activeCoupons);
    }
}