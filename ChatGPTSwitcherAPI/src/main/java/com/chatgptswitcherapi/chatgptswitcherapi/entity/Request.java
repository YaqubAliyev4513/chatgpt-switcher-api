package com.chatgptswitcherapi.chatgptswitcherapi.entity;


public class Request {
  private String model = "text-davinci-003";
  private String prompt;
  private int temperature = 0;
  private int max_tokens = 1000;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getModel() {
        return model;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getMax_tokens() {
        return max_tokens;
    }
}
