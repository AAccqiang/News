package com.example.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.news.R;
import com.example.news.base.BaseActivity;
import com.example.news.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 86409 on 2016/12/5.
 */

public class UserActivity extends BaseActivity {

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logout)
    public void logout(){
        SharedPreferencesUtils.saveName(this,"");
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.im_head_left)
    public void back(){
        finish();
    }
}
