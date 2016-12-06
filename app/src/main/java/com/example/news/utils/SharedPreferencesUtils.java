package com.example.news.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.news.entity.BaseEntity;
import com.example.news.entity.UserResponse;

/**
 * Created by 86409 on 2016/12/5.
 */

public class SharedPreferencesUtils {
    public static  void  saveBaseEntity(Context context, BaseEntity<UserResponse> baseEntity){
        SharedPreferences sp = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("message",baseEntity.getMessage());
        editor.putString("status",baseEntity.getStatus());
        editor.putInt("result",baseEntity.getData().getResult());
        editor.putString("explain",baseEntity.getData().getExplanin());
        editor.putString("token",baseEntity.getData().getToken());
        editor.commit();
    }

    public static BaseEntity<UserResponse> readBaseEntity(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String explain = sp.getString("explain","");
        String token = sp.getString("token","");
        int result = sp.getInt("result",0);
        String message = sp.getString("message","");
        String status = sp.getString("status","");

        UserResponse response = new UserResponse();
        response.setExplanin(explain);
        response.setResult(result);
        response.setToken(token);

        BaseEntity<UserResponse> entity = new BaseEntity<UserResponse>(){};
        entity.setData(response);
        entity.setMessage(message);
        entity.setStatus(status);

        return entity;
    }

    public static  void  saveName(Context context,String name){
        SharedPreferences sp = context.getSharedPreferences("user_name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.commit();
    }

    public static String readName(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String name = sp.getString("name","");
        return name;
    }
}
