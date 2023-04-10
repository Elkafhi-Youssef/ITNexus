package com.itnexusglobal.itnexusglobal.response;

public class AuthResponse {
    private String token;
    private long id;
    private String message;


    public AuthResponse() {
    }

    public AuthResponse(String mssage) {
        this.message = mssage;
    }
    public AuthResponse(String message, Integer status) {
        this.message = message;
    }
    public AuthResponse(String message, Integer status, String token) {
        this.message = message;
        this.token = token;
    }
    public AuthResponse(String message, Long id, String jwtAccessToken) {
        this.message = message;
        this.id = id;
        this.token = jwtAccessToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
