package src.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import src.springboot.dto.NewLoginRequest;
import src.springboot.entities.ClientType;
import src.springboot.service.ClientService;
import src.springboot.strategy.impl.DefaultLoginStrategy;

import java.sql.SQLException;

@RestController
public abstract class ClientController {
    @PostMapping("/login")
    public boolean login(@RequestBody NewLoginRequest newLoginRequest) {
        try {
            ClientType clientType = getClientType();
            ClientService clientService = DefaultLoginStrategy.getFacadeByLogin(
                    newLoginRequest.getUsername(),
                    newLoginRequest.getPassword(),
                    clientType
            );
            return clientService != null;
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected abstract ClientType getClientType();
}
