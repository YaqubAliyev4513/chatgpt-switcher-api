package com.chatgptswitcherapi.chatgptswitcherapi.advice;


import com.chatgptswitcherapi.chatgptswitcherapi.entity.Message;
import com.chatgptswitcherapi.chatgptswitcherapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

@ExceptionHandler(AccountTokenWrongException.class)
public ResponseEntity<Message> handleAccountWrongTokenException(AccountTokenWrongException exception){
    return new ResponseEntity<Message>(new Message(exception.getMessage()),HttpStatus.UNAUTHORIZED);
}

@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<Message> handleBadRequestProblem(HttpMessageNotReadableException exception){
    return  new ResponseEntity<Message>(new Message("Required request body is missing"), HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(AccountUsageOverLimitException.class)
 public ResponseEntity<Message> handleAccountUsageOverLimitException(AccountUsageOverLimitException exception){
    return  new ResponseEntity<Message>(new Message(exception.getMessage()),HttpStatus.TOO_MANY_REQUESTS);
}
@ExceptionHandler(IndexOutOfBoundsException.class)
 public ResponseEntity<Message> handleIndexOutOfBoundsException(IndexOutOfBoundsException exception){
    return  new ResponseEntity<>(new Message("There are no active gpt accounts in database"),HttpStatus.CONFLICT);
}
@ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Message> handleUserExistsException(UserExistsException exc){
    return new ResponseEntity<>(new Message(exc.getMessage()),HttpStatus.CONFLICT);
}
@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Message> handleUserNotFoundException(UserNotFoundException exception){
    return  new ResponseEntity<>(new Message(exception.getMessage()),HttpStatus.NOT_FOUND);
}
@ExceptionHandler(UserIsNotOnlineException.class)
 public ResponseEntity<Message> handleUserIsNotOnlineException(UserIsNotOnlineException exception){
    return  new ResponseEntity<>(new Message(exception.getMessage()),HttpStatus.UNAUTHORIZED);
}

@ExceptionHandler(LoginCredentialsWrongException.class)
    public ResponseEntity<Message> handleLoginCredentialsWrongException(LoginCredentialsWrongException exception){
    return  new ResponseEntity<>(new Message(exception.getMessage()),HttpStatus.FORBIDDEN);
}

@ExceptionHandler(TalkForbiddenException.class)
    public ResponseEntity<Message> handleTalkForbiddenException(TalkForbiddenException exception){
    return new ResponseEntity<>(new Message(exception.getMessage()),HttpStatus.FORBIDDEN);
}



}
