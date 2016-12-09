package com.example.news.MenuFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.activity.UserActivity;
import com.example.news.base.BaseActivity;
import com.example.news.biz.VersonManager;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.MessageEvent;
import com.example.news.entity.UserResponse;
import com.example.news.entity.Version;
import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.LoadImage;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.SharedPreferencesUtils;
import com.example.news.utils.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

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

//    @Override
//    public void onStart() {
//        super.onStart();
//        List<String> list = SharedPreferencesUtils.readName(getActivity());
//        SharedPreferences sp = getActivity().getSharedPreferences("users",Context.MODE_PRIVATE);
//        String img = sp.getString("headImage","");
//
//         String  name = list.get(0);
//        if(!name.equals("")){
//            llLogin.setVisibility(View.VISIBLE);
//            llUnLogin.setVisibility(View.INVISIBLE);
//            tvLogin.setText(name);
//        }else{
//            llLogin.setVisibility(View.INVISIBLE);
//            llUnLogin.setVisibility(View.VISIBLE);
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        if(SharedPreferencesUtils.isLogin(getActivity())){
            llLogin.setVisibility(View.VISIBLE);
            llUnLogin.setVisibility(View.GONE);

            SharedPreferences sp = getActivity().getSharedPreferences("users",Context.MODE_PRIVATE);
            String username = sp.getString("name","");
            String headImage = sp.getString("headImage","");
            String localHeadImage = sp.getString("localHeadImage","");
            tvLogin.setText(username);
            File file = new File(localHeadImage);
            if(file.exists()){

                ivLogin.setImageResource(R.drawable.userbg);
                ivLogin.setImageURI(Uri.fromFile(file));
            }else{
                ivLogin.setImageResource(R.drawable.userbg);
                LoadImage loadImage = new LoadImage(getActivity());
                loadImage.displayBitmap(headImage,ivLogin);
            }

        }else{
            llLogin.setVisibility(View.GONE);
            llUnLogin.setVisibility(View.VISIBLE);
        }
    }

    private void  logined(){
        List<String> list = SharedPreferencesUtils.readName(getActivity());
        final String  username = list.get(0);
        final String password = list.get(1);

        OkHttpUtils.doGet(Url.LOGIN + "?ver=0&device=0&uid=" + username + "&pwd=" + password, new SuccessListener() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                BaseEntity<UserResponse> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<UserResponse>>(){}.getType());
                CommonUtils.showShort(getActivity(),baseEntity.getStatus());
                if(baseEntity.getStatus().equals("0")){
                    if(baseEntity.getData().getResult() == 0){


                        SharedPreferencesUtils.saveName(getActivity(),username,password);
                        SharedPreferencesUtils.saveBaseEntity(getActivity(),baseEntity);

                        MessageEvent messageEvent = new MessageEvent();
                        messageEvent.setType(MessageEvent.TYPE_MAIN_FRAGMENT);
                        EventBus.getDefault().post(messageEvent);

                        startActivity(new Intent(getActivity(), UserActivity.class));

                    }else{
                        CommonUtils.showShort(getActivity(),baseEntity.getData().getExplanin());
                    }
                }else{
                    CommonUtils.showShort(getActivity(),"登陆失败，请重试");
                }
            }
        }, new FailListener() {
            @Override
            public void onFail(String error) {

            }
        });
    }



    @OnClick(R.id.im_right_login)
    public  void toUser(){
//        MessageEvent messageEvent = new MessageEvent();
//        messageEvent.setType(MessageEvent.TYPE_MAIN_FRAGMENT);
//        EventBus.getDefault().post(messageEvent);
//
//
//        BaseActivity activity = (BaseActivity) getActivity();
//        activity.startActivity(UserActivity.class);

        logined();
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


    @OnClick(R.id.tv_update_version)
    public void getUpdateVersion(){
        VersonManager.getUpdateVersion(getActivity(),successListener,failListener);
    }
    private SuccessListener successListener = new SuccessListener() {
        @Override
        public void onSuccess(String json) {
            System.out.println(json);
            Gson gson = new Gson();
            Version version = gson.fromJson(json, Version.class);
            if(version.getVersion().equals(getActivity().getPackageManager().getPackageInfo()))

        }
    };

    private FailListener failListener = new FailListener() {
        @Override
        public void onFail(String error) {

        }
    };

}
