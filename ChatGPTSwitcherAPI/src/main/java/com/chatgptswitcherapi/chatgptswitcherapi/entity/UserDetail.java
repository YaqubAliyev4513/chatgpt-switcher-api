package com.chatgptswitcherapi.chatgptswitcherapi.entity;

import java.util.List;

public class UserDetail {
    private User user;
    private List<RequestDetail> details;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RequestDetail> getDetails() {
        return details;
    }

    public void setDetails(List<RequestDetail> details) {
        this.details = details;
    }
}
