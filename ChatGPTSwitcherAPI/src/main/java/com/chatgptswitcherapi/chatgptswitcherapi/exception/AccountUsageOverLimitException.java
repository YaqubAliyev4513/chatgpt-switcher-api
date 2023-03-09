package com.chatgptswitcherapi.chatgptswitcherapi.exception;

public class AccountUsageOverLimitException extends RuntimeException{
    public AccountUsageOverLimitException(String message){
        super(message);
    }
}
