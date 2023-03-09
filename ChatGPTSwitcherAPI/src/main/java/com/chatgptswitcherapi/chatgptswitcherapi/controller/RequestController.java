package com.chatgptswitcherapi.chatgptswitcherapi.controller;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Request;
import com.chatgptswitcherapi.chatgptswitcherapi.entity.Response;
import com.chatgptswitcherapi.chatgptswitcherapi.request.AuthRequest;
import com.chatgptswitcherapi.chatgptswitcherapi.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RequestMapping("/request")
@RestController
public class RequestController {


   @Autowired
   private RequestService requestService;
   @GetMapping("/send/{prompt}")
   public ResponseEntity<Response> sendRequest(@PathVariable String prompt) {
      Request request = new Request();
      request.setPrompt(prompt);
      Response response = requestService.sendRequest(request);
      return new ResponseEntity<>(response,HttpStatus.OK);
   }

   @Transactional
   @GetMapping("/authsend/{username}/{prompt}")
   public ResponseEntity<Response> sendAuthRequest(@PathVariable String username,@PathVariable String prompt){
      AuthRequest authRequest = new AuthRequest();
      authRequest.setUsername(username);
      Request r = new Request();
      r.setPrompt(prompt);
      authRequest.setRequest(r);
      Response response = requestService.sendAuthRequest(authRequest);
      return new ResponseEntity<>(response,HttpStatus.OK);
   }

}
