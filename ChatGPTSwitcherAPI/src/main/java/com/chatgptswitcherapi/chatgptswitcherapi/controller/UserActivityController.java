package com.chatgptswitcherapi.chatgptswitcherapi.controller;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.User;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.UserActivity;
import com.chatgptswitcherapi.chatgptswitcherapi.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/activity")
@RestController
public class UserActivityController {

    @Autowired
    private UserActivityService userActivityService;

    @GetMapping("/online")
    public ResponseEntity<UserActivity> howManyUsersOnline(){
        UserActivity userActivity = new UserActivity();
        userActivity.setHowManyUsersOnline(userActivityService.howManyUsersOnline());

        return new ResponseEntity<>(userActivity, HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<UserActivity> getUsers(){
        UserActivity userActivity = new UserActivity();
        userActivity.setHowManyUsersOnline(userActivityService.howManyUsersOnline());

        userActivity.setUsersDetails(userActivityService.getUsersDetailised());
        return new ResponseEntity<>(userActivity,HttpStatus.OK);
    }
}
