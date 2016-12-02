package com.example.news.utils;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.news.R;

/**
 * Created by 86409 on 2016/11/28.
 */

public class VolleyHttp {
    private Context context;
    private static RequestQueue requestQueue;

    public VolleyHttp(Context context) {
        this.context = context;

        if(this.requestQueue == null){
            this.requestQueue = Volley.newRequestQueue(context);
        }
    }

    public void sendStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
        StringRequest request = new StringRequest(url,listener,errorListener);
        requestQueue.add(request);
    }

    //发送请求，获取图片
    public void sendImageViewRequest(String url, ImageLoader.ImageCache imageCache, ImageView imageView){
        ImageLoader imageLoader = new ImageLoader(requestQueue,imageCache);
        ImageLoader.ImageListener imageListener =ImageLoader.getImageListener(imageView, R.drawable.defaultpic,android.R.drawable.ic_delete);
        imageLoader.get(url,imageListener);
    }

}
