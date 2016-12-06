package com.example.news.utils;

import android.os.Handler;
import android.os.Message;

import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 86409 on 2016/12/5.
 */

public class OkHttpUtils {


    private static OkHttpClient httpClient = new OkHttpClient();
    private static  Handler handler = new Handler();

    public static  void doGet(String url,final SuccessListener successListener,final  FailListener failListener){
        Request request = new Request.Builder().url(url).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String error = e.getMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    failListener.onFail(error);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String json = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            successListener.onSuccess(json);
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            failListener.onFail("访问失败");
                        }
                    });
                }
            }
        });
    }

}
