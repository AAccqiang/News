package com.example.news.biz;

import android.content.Context;

import com.android.volley.Response;
import com.example.news.utils.Url;
import com.example.news.utils.VolleyHttp;

/**
 * Created by 86409 on 2016/12/1.
 */

public class CommentManager {

    public static void getCommentNum(Context context, int nid, Response.Listener<String> listener, Response.ErrorListener errorListener){
        VolleyHttp volleyHttp = new VolleyHttp(context);
        volleyHttp.sendStringRequest(Url.GET_CONNECT_NUM + "?ver=0&nid=" + nid,listener,errorListener);
    }
}
