package com.example.news.biz;

import android.content.Context;

import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.SharedPreferencesUtils;
import com.example.news.utils.Url;

import java.io.File;

/**
 * Created by 86409 on 2016/12/7.
 */

public class UserManager {
    public  static void getUserInfo(Context context, SuccessListener successListener, FailListener failListener){
        String imei = CommonUtils.getIMEI(context);
        String token = SharedPreferencesUtils.getToken(context);
        System.out.println(token);
        OkHttpUtils.doGet(Url.USER_HOME+"?ver=0&imei="+imei+"&token="+token,successListener,failListener);
    }

    public static  void uploadUserImage(Context context, String url, File file,SuccessListener successListener,FailListener failListener){
        String token = SharedPreferencesUtils.getToken(context);
        OkHttpUtils.updateFile(url,token,file,successListener,failListener);
    }


}
