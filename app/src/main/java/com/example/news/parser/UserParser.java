package com.example.news.parser;

import com.example.news.entity.BaseEntity;
import com.example.news.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by 86409 on 2016/12/7.
 */

public class UserParser {
    public static BaseEntity<User> parseUser(String json){
        Gson gson = new Gson();
        BaseEntity<User> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<User>>(){}.getType());
        return baseEntity;
    }
}
