package com.chatgptswitcherapi.chatgptswitcherapi.service;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Account;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.Message;
import com.chatgptswitcherapi.chatgptswitcherapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Message add(Account account){
        accountRepository.save(account);
        return  new Message("Account created");
    }
    public Message delete(String apiKey){
        accountRepository.deleteByAccountKey(apiKey);
        return  new Message("Account deleted");
    }
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }


}
