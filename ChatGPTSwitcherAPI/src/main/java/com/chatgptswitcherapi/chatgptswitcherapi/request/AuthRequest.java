package com.chatgptswitcherapi.chatgptswitcherapi.request;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Request;

public class AuthRequest {
    private String username;

    private Request request;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
