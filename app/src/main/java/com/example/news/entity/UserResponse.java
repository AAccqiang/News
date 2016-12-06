package com.example.news.entity;

/**
 * Created by 86409 on 2016/12/5.
 */

public class UserResponse {
    private int result;
    private String explanin;
    private String token;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getExplanin() {
        return explanin;
    }

    public void setExplanin(String explanin) {
        this.explanin = explanin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
