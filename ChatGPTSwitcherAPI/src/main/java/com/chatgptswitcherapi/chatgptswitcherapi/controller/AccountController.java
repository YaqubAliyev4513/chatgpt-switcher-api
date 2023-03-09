package com.chatgptswitcherapi.chatgptswitcherapi.controller;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Account;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.Message;
import com.chatgptswitcherapi.chatgptswitcherapi.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @GetMapping("/add/{apiKey}")
    public ResponseEntity<Message> addAccount(@PathVariable String apiKey){
      Account account = new Account();
      account.setAccountKey(apiKey);
      Message response =  accountService.add(account);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/delete/{apikey}")
    public ResponseEntity<Message> deleteAccount(@PathVariable String apikey){
        Message response =  accountService.delete(apikey);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> getAccounts(){
        return new ResponseEntity<>(accountService.getAccounts(),HttpStatus.OK);
    }

}
