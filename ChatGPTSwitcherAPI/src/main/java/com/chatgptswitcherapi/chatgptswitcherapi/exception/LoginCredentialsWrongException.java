package com.chatgptswitcherapi.chatgptswitcherapi.exception;

public class LoginCredentialsWrongException extends RuntimeException{
    public LoginCredentialsWrongException(String message){
        super(message);
    }
}
