package com.chatgptswitcherapi.chatgptswitcherapi.exception;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
