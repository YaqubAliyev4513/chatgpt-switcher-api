package com.chatgptswitcherapi.chatgptswitcherapi.entity;

import java.util.ArrayList;
import java.util.List;

public class UserActivity {
    private int howManyUsersOnline;

    private List<UserDetail> usersDetails;


    public List<UserDetail> getUsersDetails() {
        return usersDetails;
    }

    public void setUsersDetails(List<UserDetail> usersDetails) {
        this.usersDetails = usersDetails;
    }

    public int getHowManyUsersOnline() {
        return howManyUsersOnline;
    }

    public void setHowManyUsersOnline(int howManyUsersOnline) {
        this.howManyUsersOnline = howManyUsersOnline;
    }


}
