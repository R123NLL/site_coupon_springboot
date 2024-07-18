package src.springboot.dto;

import src.springboot.entities.ClientType;

public class NewLoginRequest {
    private String username;
    private String password;

    private ClientType clientType;

    public NewLoginRequest() {
    }

    public NewLoginRequest(String username, String password, ClientType clientType) {
        this.username = username;
        this.password = password;
        this.clientType = clientType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
