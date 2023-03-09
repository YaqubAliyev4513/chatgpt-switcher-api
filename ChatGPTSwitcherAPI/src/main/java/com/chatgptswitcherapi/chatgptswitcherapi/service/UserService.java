package com.chatgptswitcherapi.chatgptswitcherapi.service;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Message;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.User;
import com.chatgptswitcherapi.chatgptswitcherapi.exception.LoginCredentialsWrongException;
import com.chatgptswitcherapi.chatgptswitcherapi.exception.UserExistsException;
import com.chatgptswitcherapi.chatgptswitcherapi.exception.UserIsNotOnlineException;
import com.chatgptswitcherapi.chatgptswitcherapi.exception.UserNotFoundException;
import com.chatgptswitcherapi.chatgptswitcherapi.repository.UserRepository;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserLogin;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserLogout;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public User getUser(String username){
       User user = userRepository.findByUsername(username);
       if(user == null){
           throw new UserNotFoundException("user not found");
       }
       return  user;
   }

   public void addUser(UserRequest userRequest){
       User exists = userRepository.findByUsername(userRequest.getUsername());
       if(exists!=null){
           throw new UserExistsException("user already exists in database");
       }
       String referal = userRequest.getReferal();
       User isRealReferal =  userRepository.findByUsername(referal);
       if(isRealReferal == null){
           System.out.println("null du bu");
       }else{
           if(isRealReferal.getRefers()+1==2){
               userRepository.updateUserLevelAndTalk("vip",3000, isRealReferal.getId());
           }
           userRepository.updateUserReferalCount(isRealReferal.getRefers()+1, isRealReferal.getId());
       }
       User u = new User();
       u.setUsername(userRequest.getUsername());
       u.setPassword(userRequest.getPassword());
       u.setRegisterTime(LocalDateTime.now());
       userRepository.save(u);
   }

   public void login(UserLogin userLogin){
        User user = userRepository.findByUsername(userLogin.getUsername());
        if(user == null){
            throw new UserNotFoundException("user doesnt exists in database");
        }
        if(!user.getUsername().equals(userLogin.getUsername()) || !user.getPassword().equals(userLogin.getPassword())){
            throw new LoginCredentialsWrongException("username and password are wrong");
        }
        userRepository.loginCondition(true,user.getId());

   }
   public void logOut(UserLogout userLogout){
       User user = userRepository.findByUsername(userLogout.getUsername());
       if(user == null){
           throw new UserNotFoundException("user doesnt exists in database");
       }
       if(user.isOnline()==false){
           throw new UserIsNotOnlineException("user is not online right now");
       }
       userRepository.loginCondition(false,user.getId());

   }

}
