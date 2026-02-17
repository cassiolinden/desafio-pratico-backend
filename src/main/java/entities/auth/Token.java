package entities.auth;

import java.util.Date;

public class Token {
    private String token;
    private Date expiry;

    // Constructors
    public Token() {}

    public Token(String token) {
        this.token = token;
        this.expiry = createExpiryTimestamp();
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    private Date createExpiryTimestamp() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(java.util.Calendar.HOUR_OF_DAY, 1);
        return new Date(cal.getTime().getTime());
    }
}