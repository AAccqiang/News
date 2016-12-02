package com.example.news.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.base.BaseActivity;
import com.example.news.biz.CommentManager;
import com.example.news.db.NewsDBManager;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.News;
import com.example.news.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 86409 on 2016/12/1.
 */

public class ShowNewsActivity extends BaseActivity{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tv_comment_num)
    TextView tvComment_num;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private News news;
    private PopupWindow popupWindow;
    private NewsDBManager newsDbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CommonUtils.isNewConnect(this)){
            setContentView(R.layout.loading_fail);

        }else{


            setContentView(R.layout.activity_show_news);

            newsDbManager = new NewsDBManager(this);
            ButterKnife.bind(this);
            Intent intent = getIntent();
            news= (News) intent.getSerializableExtra("news");
            webView.getSettings().setJavaScriptEnabled(true);
             webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.getSettings().setAllowContentAccess(true);
            webView.setWebChromeClient(webChromeClient);
            webView.setWebViewClient(webViewClient);
            webView.loadUrl(news.getLink());

            CommentManager.getCommentNum(this,news.getNid(),listener,errorListener);
            initPopupWindow();


        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if(newProgress >= 100){
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    public void initPopupWindow(){
        View view = getLayoutInflater().inflate(R.layout.popup_window,null);
        TextView tvCollectNews = (TextView) view.findViewById(R.id.tv_collenct_news);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());//只要不会

        tvCollectNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                if(!newsDbManager.isCollectNews(news)){
                    newsDbManager.save(news);
                    CommonUtils.showShort(ShowNewsActivity.this,"收藏新闻成功");

                }else{
                    CommonUtils.showShort(ShowNewsActivity.this,"已收藏，可以在收藏里面查看");
                }
            }
        });

    }
    @OnClick(R.id.iv_menu)
    public  void  showMenu(){
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }else if(popupWindow != null){
            popupWindow.showAsDropDown(ivMenu);
        }
    }

    @OnClick(R.id.iv_back)public void back(){
        finish();
    }

    @OnClick(R.id.tv_comment_num)public void gotoComment(){
        Bundle bundle = new Bundle();
        bundle.putInt("nid",news.getNid());
        startActivity(CommentActivity.class,bundle);
    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Gson gson = new Gson();
            BaseEntity<Integer> baseEntity = gson.fromJson(s,new TypeToken<BaseEntity<Integer>>(){}.getType());
            int commentNum = baseEntity.getData();
            tvComment_num.setText(commentNum + "跟帖");
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CommonUtils.showShort(ShowNewsActivity.this,"加载评论失败");
        }
    };
}
