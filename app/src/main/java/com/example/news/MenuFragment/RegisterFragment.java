package com.example.news.MenuFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.UserResponse;
import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.SharedPreferencesUtils;
import com.example.news.utils.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 86409 on 2016/12/2.
 */

public class RegisterFragment extends Fragment {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,view);
        return view;
    }


    @OnClick(R.id.btn_register)
    public void register(){
        String email = etEmail.getText().toString();
        String username = etNickname.getText().toString();
        String password = etPassword.getText().toString();
        boolean isAgree = cbAgree.isChecked();

        if(!isAgree){
            Toast.makeText(getActivity(),"必须同意条款才可以注册",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!CommonUtils.verifyEmail(email)){
            CommonUtils.showShort(getActivity(),"请输入正确的邮箱");
            return;
        }

        if(!CommonUtils.veriftName(username)){
            CommonUtils.showShort(getActivity(),"用户名必须符合要求");
            return;
        }

        if(TextUtils.isEmpty(password)){
            CommonUtils.showShort(getActivity(),"密码不能为空");
            return;
        }

        if(!CommonUtils.veriftPassword(password)){
            CommonUtils.showShort(getActivity(),"密码必须在6~24位之间");
            return;
        }

        OkHttpUtils.doGet(Url.REGISITER + "?ver=0&uid="+username+"&pwd="+password+"&email="+email,successListener,failListener);
    }

    private SuccessListener successListener = new SuccessListener() {
        @Override
        public void onSuccess(String json) {
            Gson gson = new Gson();

            BaseEntity<UserResponse> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<UserResponse>>(){}.getType());
            if(baseEntity.getStatus().equals("0")){
                if(baseEntity.getData().getResult() == 0){
                    SharedPreferencesUtils.saveBaseEntity(getActivity(),baseEntity);
                    CommonUtils.showShort(getActivity(),"注册成功");
                    return;
                }
                if(baseEntity.getData().getResult() == -1){
                    CommonUtils.showShort(getActivity(),"邮箱已被注册");
                    return;
                }
                if(baseEntity.getData().getResult() == -2){
                    CommonUtils.showShort(getActivity(),"用户名已被注册");
                    return;
                }
                //CommonUtils.showShort(getActivity(),baseEntity.getData().getExplanin());
            }
            else{
                CommonUtils.showShort(getActivity(),"注册失败，请重试");
            }
        }
    };

    private FailListener failListener = new FailListener() {
        @Override
        public void onFail(String error) {
            CommonUtils.showShort(getActivity(),error);
        }
    };

}
