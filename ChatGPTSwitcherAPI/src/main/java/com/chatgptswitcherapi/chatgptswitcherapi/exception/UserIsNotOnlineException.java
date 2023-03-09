package com.chatgptswitcherapi.chatgptswitcherapi.exception;

public class UserIsNotOnlineException extends RuntimeException{
    public UserIsNotOnlineException(String message){
        super(message);
    }
}
