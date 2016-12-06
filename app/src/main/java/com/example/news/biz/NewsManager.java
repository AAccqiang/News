package com.example.news.biz;

import android.content.Context;

import com.android.volley.Response;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.Url;
import com.example.news.utils.VolleyHttp;

/**
 * Created by 86409 on 2016/11/28.
 */

public class NewsManager {

    public static  final  int MODE_PULL_REFRESH = 1 ;
    public static  final  int MODE_LOAD_MORE = 2;

    //发送请求获取新闻分类
    public static  void getNewsType(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener){
        String imei = CommonUtils.getIMEI(context);

        VolleyHttp http = new VolleyHttp(context);
        http.sendStringRequest(Url.GET_NETS_TYPE+"?ver=0&imei="+ imei,listener,errorListener);

    }

    public static  void getNewsList(Context context, int subid, int dir, int nid,Response.Listener<String> listener,
                                    Response.ErrorListener errorListener){

        String stamp = CommonUtils.getCurrentData();
        VolleyHttp http = new VolleyHttp(context);
        http.sendStringRequest(Url.GET_NEWS + "?ver=0&subid=" + subid +"&dir=" + dir + "&nid="+ nid + "&stamp=" + stamp +
                                "&cnt=20",listener,errorListener);
    }

    public static void  getComment(Context context, int nid, int dir,int cid, Response.Listener<String> listener, Response.ErrorListener errorListener){
        String stamp = CommonUtils.getCurrentData();
        VolleyHttp http = new VolleyHttp(context);
        http.sendStringRequest(Url.COMMENT + "?ver=0&nid="+nid+"&type=1&stamp="+stamp+"&cid="+cid+"&dir="+dir+"&cnt=20",listener,errorListener);
    }

}
