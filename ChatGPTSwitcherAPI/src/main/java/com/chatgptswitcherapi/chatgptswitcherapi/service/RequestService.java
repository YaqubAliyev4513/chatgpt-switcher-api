package com.chatgptswitcherapi.chatgptswitcherapi.service;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.*;
import com.chatgptswitcherapi.chatgptswitcherapi.exception.*;
import com.chatgptswitcherapi.chatgptswitcherapi.repository.AccountRepository;

import com.chatgptswitcherapi.chatgptswitcherapi.repository.RequestDetailRepository;
import com.chatgptswitcherapi.chatgptswitcherapi.repository.UserRepository;
import com.chatgptswitcherapi.chatgptswitcherapi.request.AuthRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestDetailRepository requestDetailRepository;

    private int tokensUsed;

    public Response sendRequest(Request request){
        Account currentActiveAccount = accountRepository.findAllByAccountLimit(false).get(0);
        System.out.println(currentActiveAccount);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/completions";
        HttpHeaders headers = new HttpHeaders();
        String apiToken  = currentActiveAccount.getAccountKey();
        headers.setBearerAuth(apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<Request> requestEntity = new HttpEntity<>(request,headers);
        ResponseEntity<String> responseEntity;

        try{
            responseEntity =  restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
        }catch(HttpClientErrorException exception){
             throw new AccountTokenWrongException("api token is wrong");
        }


        String response = responseEntity.getBody();
        int statusCode = responseEntity.getStatusCode().value();

        if(statusCode == 429){
            accountRepository.delete(currentActiveAccount);
            currentActiveAccount.setAccountLimit(true);
            accountRepository.save(currentActiveAccount);
            throw  new AccountUsageOverLimitException("system overloaded");
        }else{
            Response result = new Response();

            try{
                JsonNode node = mapper.readTree(response);
                JsonNode choices = node.get("choices");
                JsonNode usage = node.get("usage");
                JsonNode totalTokens = usage.get("total_tokens");
                tokensUsed = totalTokens.asInt();
                for(JsonNode node1: choices){
                    result.setResponse(node1.get("text").asText());
                }
            }catch(Exception exc){
                System.out.println(exc.getMessage());
            }

            return  result;
        }

    }
    public Response sendAuthRequest(AuthRequest authRequest){
        String username = authRequest.getUsername();
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException("user doesnt exists");
        }
        if(user.isOnline() == false){
            throw new UserIsNotOnlineException("user is not online right now");
        }
        if(user.getTalk() == 0){
            throw new TalkForbiddenException("talk quantity is zero right now");
        }
        Response response = sendRequest(authRequest.getRequest());
        RequestDetail requestDetail = new RequestDetail();
        requestDetail.setUserId(user.getId());
        requestDetail.setQuestion(authRequest.getRequest().getPrompt());
        requestDetail.setReply(response.getResponse());
        requestDetailRepository.save(requestDetail);

        userRepository.updateUserTalk(user.getTalk()-1,user.getId());
        userRepository.updateUserLastUseTime(LocalDateTime.now(),user.getId());
        userRepository.updateUserTokensUsed(user.getTokensUsed()+tokensUsed,user.getId());
        return response;
    }




}
