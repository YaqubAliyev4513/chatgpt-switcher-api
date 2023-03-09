package com.chatgptswitcherapi.chatgptswitcherapi.service;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.RequestDetail;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.User;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.UserDetail;
import com.chatgptswitcherapi.chatgptswitcherapi.repository.RequestDetailRepository;
import com.chatgptswitcherapi.chatgptswitcherapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserActivityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestDetailRepository requestDetailRepository;

    public int howManyUsersOnline(){

        return userRepository.findAllByOnline(true).size();
    }

    public List<UserDetail> getUsersDetailised(){
        List<UserDetail> userDetails = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for(int i = 0;i<users.size();i++){
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(users.get(i));
            List<RequestDetail> usersRequests = requestDetailRepository.findByUserId(users.get(i).getId());
            userDetail.setDetails(usersRequests);
            userDetails.add(userDetail);
        }

        return userDetails;
    }






}
