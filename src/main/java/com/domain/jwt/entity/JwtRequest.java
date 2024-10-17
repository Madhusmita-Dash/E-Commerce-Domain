package com.domain.jwt.entity;

public class JwtRequest {

    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserpassword() {
        return userPassword;
    }

    public void setUserpassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
