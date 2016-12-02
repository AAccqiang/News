package com.example.news.MenuFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.news.R;
import com.example.news.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 86409 on 2016/12/2.
 */

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
}
