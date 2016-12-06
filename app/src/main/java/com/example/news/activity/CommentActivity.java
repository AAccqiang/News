package com.example.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.adapter.CommentAdapter;
import com.example.news.base.BaseActivity;
import com.example.news.biz.NewsManager;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.CommentEntity;
import com.example.news.entity.UserResponse;
import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.parser.NewsParser;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.SharedPreferencesUtils;
import com.example.news.utils.Url;
import com.example.news.xListView.XListView;
import com.google.gson.Gson;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CommentActivity extends BaseActivity{

    @BindView(R.id.listView)
    XListView listView;

    @BindView(R.id.et_content)
    EditText etContent;

    @BindView(R.id.im_head_left)
    ImageView imBack;

    private CommentAdapter adapter;
    private int refreshMode = 1;
    int dir = 1;
    int nid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        adapter = new CommentAdapter(this);

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        nid = bundle.getInt("nid",0);
        refreshMode = NewsManager.MODE_PULL_REFRESH;

        int cid = 10;
        NewsManager.getComment(this,nid,dir,cid,listener,errorListener);
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        listView.setXListViewListener(listViewListener);

        refreshMode = NewsManager.MODE_PULL_REFRESH;
        loadNewX(true);
    }

    private XListView.IXListViewListener listViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            refreshMode = NewsManager.MODE_PULL_REFRESH;
            loadNewX(false);
        }

        @Override
        public void onLoadMore() {
            refreshMode = NewsManager.MODE_LOAD_MORE;
            loadPrevNews();
        }
    };


    private void loadNewX(boolean isNewType){
        int cid = 1;
        if(!isNewType){
            if(adapter.getData().size() > 0 ){
                cid = adapter.getData().get(0).getCid();
            }
        }

        if(CommonUtils.isNewConnect(this)){
            NewsManager.getComment(this,nid,dir,cid,listener,errorListener);
        }
    }
    private void loadPrevNews(){
        if(adapter.getData().size() == 0){return;}
        int lastIndex = adapter.getData().size() - 1;
        int cid = adapter.getData().get(lastIndex).getCid();
        int dir = 2;
        if(CommonUtils.isNewConnect(this)){
            NewsManager.getComment(this,nid,dir,cid,listener,errorListener);
        }else{

        }
    }



    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            System.out.println(response);
            List<CommentEntity> list = NewsParser.parseComment(response);
            boolean isClear = refreshMode == NewsManager.MODE_PULL_REFRESH ? true : false;
            adapter.appendDataToAdapter(list,isClear);
            adapter.notifyDataSetChanged();

            listView.stopLoadMore();//停止
            listView.stopRefresh();
            listView.setRefreshTime(CommonUtils.getCurrentData());
            cancleDialog();
        }
    };



    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CommonUtils.showShort(CommentActivity.this,"访问失败");
        }
    };

    @OnClick(R.id.iv_send)
    public void send(){
        String content = etContent.getText().toString();
        BaseEntity<UserResponse> entity = SharedPreferencesUtils.readBaseEntity(this);
        String token = entity.getData().getToken();
        String imei = CommonUtils.getIMEI(this);
        String name = SharedPreferencesUtils.readName(this);
        if(name.equals("")){
            CommonUtils.showShort(this,"请登陆后才评论");
            return;
        }

       OkHttpUtils.doGet(Url.CMT_COMMENT + "?ver=0&nid="+nid+"&token="+token+"&imei="+imei+"&ctx="+content,successListener,failListener);

    }

    private SuccessListener successListener = new SuccessListener() {
        @Override
        public void onSuccess(String json) {

                CommonUtils.showShort(CommentActivity.this,"发送成功");
                String stamp = CommonUtils.getCurrentData();
                int cid = 1;
            refreshMode = NewsManager.MODE_PULL_REFRESH;
            loadNewX(true);

          }
    };
    private FailListener failListener = new FailListener() {
        @Override
        public void onFail(String error) {

            CommonUtils.showShort(CommentActivity.this,"发送失败");
        }
    };

    @OnClick(R.id.im_head_left)
    public void back(){
        finish();
    }




}
