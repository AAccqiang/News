package com.example.news.MenuFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.news.R;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.MessageEvent;
import com.example.news.entity.UserResponse;
import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class ForgetPasswordFragment extends Fragment{

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_confirm)
    public void findPassword(){
        String emial = etEmail.getText().toString();
        if(!CommonUtils.verifyEmail(emial)){
            CommonUtils.showShort(getActivity(),"请输入正确的邮箱");
            return;
        }
        OkHttpUtils.doGet(Url.FORGET_PASSWORD + "?ver=0&email=" + emial,successListener,failListener);

    }

    private SuccessListener successListener = new SuccessListener() {
        @Override
        public void onSuccess(String json) {
            Gson gson = new Gson();
            BaseEntity<UserResponse> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<UserResponse>>(){}.getType());
            if(baseEntity.getStatus().equals("0")){
                if(baseEntity.getData().getResult() == 0){
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setType(MessageEvent.TYPE_LOGIN_FRAGMENT);
                    EventBus.getDefault().post(messageEvent);
                    CommonUtils.showShort(getActivity(),"已成功发送至邮箱");
                    return;
                }else{
                    CommonUtils.showShort(getActivity(),baseEntity.getData().getExplanin());
                }
            }else{
                CommonUtils.showShort(getActivity(),"发送失败");
            }
        }
    };

    private FailListener failListener = new FailListener() {
        @Override
        public void onFail(String error) {
            CommonUtils.showShort(getActivity(),"发送失败");
        }
    };
}
