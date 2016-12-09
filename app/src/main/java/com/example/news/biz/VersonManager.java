package com.example.news.biz;

import android.content.Context;
import android.content.pm.PackageManager;

import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.Url;

/**
 * Created by 86409 on 2016/12/8.
 */

public class VersonManager {
    //获取版本更新信息
    public static void getUpdateVersion(Context context, SuccessListener successListener, FailListener failListener){
        String imei = CommonUtils.getIMEI(context);
        String packageName = context.getPackageName();
        OkHttpUtils.doGet(Url.UPDATE_VERSION + "?ver=0&imei="+imei+"&pkg="+packageName,successListener,failListener);
    }
}
