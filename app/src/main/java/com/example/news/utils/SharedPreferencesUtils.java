package com.example.news.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.news.entity.BaseEntity;
import com.example.news.entity.UserResponse;

import java.util.ArrayList;
import java.util.List;

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

    public static  void  saveName(Context context,String name,String password){
        SharedPreferences sp = context.getSharedPreferences("user_name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("password",password);

        editor.commit();
    }

    public static List<String> readName(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String name = sp.getString("name","");
        String password = sp.getString("password","");
        List<String> list = new ArrayList<String>();
        list.add(name);
        list.add(password);
        return list;
    }

    public static  boolean isLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        String  token = sp.getString("token","");
        return TextUtils.isEmpty(token) ? false : true;
    }

    public static  String getToken(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        return sp.getString("token","");
    }

    public static void CleanData(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

        SharedPreferences sp1 = context.getSharedPreferences("user_name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp1.edit();
        editor1.clear();
        editor1.commit();

        SharedPreferences sp2 = context.getSharedPreferences("users",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sp2.edit();
        editor2.clear();
        editor2.commit();

    }

    public static void saveUserHeadImagePath(Context context,String image){
        SharedPreferences sp = context.getSharedPreferences("users",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("localHeadImage",image);
        editor.commit();
    }

    public static void  saveUser(Context context,String name,String img){
        SharedPreferences sp = context.getSharedPreferences("users",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("headImage",img);
        editor.commit();
    }




}
