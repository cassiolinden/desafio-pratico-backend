package entities.auth;

import core.ApiRequestHelper;

import java.util.HashMap;

public class Auth {
    private String username;
    private String password;

    // Constructors
    public Auth() {}

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCookieToken(HashMap<String, Object> body){
        ApiRequestHelper request = new ApiRequestHelper();
        return request.post("/auth/login", body).getCookie("token");
    }

}