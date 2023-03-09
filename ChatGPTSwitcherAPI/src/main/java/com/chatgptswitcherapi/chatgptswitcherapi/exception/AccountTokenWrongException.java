package com.chatgptswitcherapi.chatgptswitcherapi.exception;

public class AccountTokenWrongException extends RuntimeException{
    public AccountTokenWrongException(String message){
        super(message);
    }
}
