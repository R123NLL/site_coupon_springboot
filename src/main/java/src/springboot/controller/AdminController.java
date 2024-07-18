package src.springboot.controller;
import org.springframework.web.bind.annotation.*;
import src.springboot.entities.ClientType;


import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController extends ClientController{

    @Override
    protected ClientType getClientType() {
        return ClientType.Administrator;
    }
}
