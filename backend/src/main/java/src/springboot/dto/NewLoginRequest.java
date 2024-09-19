package src.springboot.dto;

import src.springboot.entities.ClientType;

public class NewLoginRequest {

    private String email;
    private String password;
    private ClientType clientType;

    public NewLoginRequest() {

    }

    public NewLoginRequest(String email, String password, ClientType clientType) {
        this.email = email;
        this.password = password;
        this.clientType = clientType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "NewLoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", clientType='" + clientType + '\'' +
                '}';
    }
}
