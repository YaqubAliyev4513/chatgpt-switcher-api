package com.chatgptswitcherapi.chatgptswitcherapi.request;

public class UserRequest {
    private String username;
    private String password;
    private String referal;

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

    public String getReferal() {
        return referal;
    }

    public void setReferal(String referal) {
        this.referal = referal;
    }
}
