package com.example.news.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 86409 on 2016/11/28.
 */

public class CommonUtils {
    //判断网络是否联通
    public static boolean isNewConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return  true;
        }else{
            return  false;
        }
    }

    //判断获取手机标识符
    public static  String getIMEI(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return  tm.getDeviceId();
    }

    //获取当前时间，并按特定格式输出
    public  static  String getCurrentData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return  sdf.format(new Date());
    }
    public static  void showShort(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static  void showLong(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    public static  boolean verifyEmail(String email){
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern  = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }

    public static  boolean veriftName(String name){
        String regex = "^[a-zA-Z]+[a-zA-Z0-9_]*$";
        return  Pattern.matches(regex,name);
    }

    public static  boolean veriftPassword(String password){
        String regex = "[a-zA-Z0-9_]{6,24}";

        return  Pattern.matches(regex,password);
    }
}
