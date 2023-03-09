package com.chatgptswitcherapi.chatgptswitcherapi.exception;

public class TalkForbiddenException extends RuntimeException{
    public TalkForbiddenException(String message){
        super(message);
    }
}
