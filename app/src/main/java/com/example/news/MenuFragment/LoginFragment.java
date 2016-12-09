package com.example.news.MenuFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.news.R;
import com.example.news.activity.UserActivity;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.MessageEvent;
import com.example.news.entity.UserResponse;
import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.SharedPreferencesUtils;
import com.example.news.utils.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class LoginFragment extends Fragment {

    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register_login)
    Button btnRegister;
    @BindView(R.id.btn_forgot_password)
    Button btnForgotPassword;
    @BindView(R.id.btn_login)
    Button btnLogon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_register_login)
    public  void publishAddRegisterFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_REGISTER_FRAGMENt);
        event.setFragmentFullName(RegisterFragment.class.getName());
        EventBus.getDefault().post(event);
    }


    @OnClick(R.id.btn_forgot_password)
    public void publishAddforgotFragmentEvent(){
        MessageEvent event = new MessageEvent();
        event.setType(MessageEvent.TYPE_FORGOT_PASSWORD);
        event.setFragmentFullName(ForgetPasswordFragment.class.getName());
        EventBus.getDefault().post(event);
    }

    @OnClick(R.id.btn_login)
    public void login(){
        String username = etNickname.getText().toString();
        String password = etPassword.getText().toString();

        if(!CommonUtils.veriftName(username)){
            CommonUtils.showShort(getActivity(),"请输入正确的用户名");
            return;
        }

        if(!CommonUtils.veriftPassword(password)){
            CommonUtils.showShort(getActivity(),"请输入正确的密码");
            return;
        }

        OkHttpUtils.doGet(Url.LOGIN + "?ver=0&device=0&uid="+username+"&pwd="+password,successListener,failListener);

    }

    private SuccessListener successListener = new SuccessListener() {
        @Override
        public void onSuccess(String json) {
            System.out.println(json + "+++++++++++++++++++++++++++++login");
            Gson gson = new Gson();
            BaseEntity<UserResponse> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<UserResponse>>(){}.getType());
            CommonUtils.showShort(getActivity(),baseEntity.getStatus());
            if(baseEntity.getStatus().equals("0")){
                if(baseEntity.getData().getResult() == 0){

                    String username = etNickname.getText().toString();
                    String password = etPassword.getText().toString();
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
    };

    private FailListener failListener = new FailListener() {
        @Override
        public void onFail(String error) {
            CommonUtils.showShort(getActivity(),"网络异常,请重试");
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        etNickname.setText("");
        etPassword.setText("");
    }
}
