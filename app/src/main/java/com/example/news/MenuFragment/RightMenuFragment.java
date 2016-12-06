package com.example.news.MenuFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.activity.UserActivity;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.MessageEvent;
import com.example.news.entity.UserResponse;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RightMenuFragment extends Fragment {
    @BindView(R.id.ll_unlogin)
    LinearLayout llUnLogin;
    @BindView(R.id.im_right_unlogin)
    ImageView ivUnLogin;
    @BindView(R.id.tv_right_unlogin)
    TextView tvUnLogin;


    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.im_right_login)
    ImageView ivLogin;
    @BindView(R.id.tv_right_login)
    TextView tvLogin;

    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.iv_qq)
    ImageView ivQQ;
    @BindView(R.id.iv_friend)
    ImageView ivFriend;
    @BindView(R.id.iv_weibo)
    ImageView ivWeibo;
    @BindView(R.id.tv_update_version)
    TextView tvUpdateVersion;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String name = SharedPreferencesUtils.readName(getActivity());

        if(!name.equals("")){
            llLogin.setVisibility(View.VISIBLE);
            llUnLogin.setVisibility(View.INVISIBLE);
            tvLogin.setText(name);
        }
    }

    @OnClick(R.id.im_right_login)
    public  void toUser(){
        Intent intent = new Intent(getActivity(), UserActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.tv_right_login)
    public void toUser2(){
        toUser();
    }



    @OnClick(R.id.im_right_unlogin)
    public void publishAddLoginFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_LOGIN_FRAGMENT);
        event.setFragmentFullName(LoginFragment.class.getName());
        EventBus.getDefault().post(event);
    }

    @OnClick(R.id.tv_right_unlogin)
    public void publistAddLoginFragmentEvent_2(){
        publishAddLoginFragmentEvent();
    }


}
