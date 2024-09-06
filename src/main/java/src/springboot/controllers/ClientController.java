package src.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import src.springboot.entities.ClientType;
import src.springboot.exceptions.LoginSecurityException;
import src.springboot.utils.LoginManager;

import java.sql.SQLException;

public abstract class ClientController {
    @Autowired
    private LoginManager loginManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, @RequestParam ClientType clientType) {
        try {
            // Call login from LoginManager and get the JWT token
            String token = loginManager.login(email, password, clientType);
            return ResponseEntity.ok(token);
        } catch (LoginSecurityException | SQLException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }
}