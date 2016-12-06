package com.example.news.parser;

import android.util.Log;

import com.example.news.entity.BaseEntity;
import com.example.news.entity.CommentEntity;
import com.example.news.entity.News;
import com.example.news.entity.NewsType;
import com.example.news.entity.SubType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 86409 on 2016/11/28.
 */

public class NewsParser {
    public  static List<SubType> parserNewsType(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<List<NewsType>>>(){}.getType();
        BaseEntity<List<NewsType>> baseEntity = gson.fromJson(json,type);
        if(baseEntity != null){
            NewsType newsType = baseEntity.getData().get(0);
            return  newsType.getSubgrp();
        }
        return  null;
    }


    public static List<News> parseNews(String json){

        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<List<News>>>(){}.getType();
        BaseEntity<List<News>> baseEntity = gson.fromJson(json,type);

        if(baseEntity != null){
            return  baseEntity.getData();
        }
        return  null;

    }


    public static  List<CommentEntity> parseComment(String json){
        Gson gson = new Gson();
        BaseEntity<List<CommentEntity>> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<List<CommentEntity>>>(){}.getType());

        if(baseEntity != null){
            return baseEntity.getData();
        }
        return null;
    }
}
