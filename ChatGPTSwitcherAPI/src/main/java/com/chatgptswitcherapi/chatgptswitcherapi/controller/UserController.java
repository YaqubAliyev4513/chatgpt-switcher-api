package com.chatgptswitcherapi.chatgptswitcherapi.controller;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Message;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.User;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserLogin;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserLogout;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserRequest;
import com.chatgptswitcherapi.chatgptswitcherapi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional
    @GetMapping ("/add/{username}/{password}/{referral}")
    public ResponseEntity<Message> addUser(@PathVariable String username,@PathVariable String password,@PathVariable String referral){
         UserRequest u = new UserRequest();
         u.setUsername(username);
         u.setPassword(password);
         u.setReferal(referral);
         userService.addUser(u);
         return new ResponseEntity<>(new Message("user created(level: free)"), HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<Message> login(@PathVariable String username,@PathVariable  String password){
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(password);
       userService.login(userLogin);
       return new ResponseEntity<>(new Message("logined"),HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/logout/{username}")
    public ResponseEntity<Message> logOut(@PathVariable String username){
        UserLogout userLogout = new UserLogout();
        userLogout.setUsername(username);
            userService.logOut(userLogout);
            return  new ResponseEntity<>(new Message("user left system"),HttpStatus.OK);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
       User u = userService.getUser(username);
       return new ResponseEntity<>(u,HttpStatus.OK);
    }
}
