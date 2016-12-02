package com.example.news.entity;

/**
 * Created by 86409 on 2016/12/2.
 */
//EventBus派发的事件实体类
public class MessageEvent {
    //将要添加LoginFragment
    public static final  int TYPE_LOGIN_FRAGMENT = 1;
    //将要添加RegisterFragment
    public static  final int TYPE_REGISTER_FRAGMENt = 2;
    //将要添加ForgitPasswordFragment
    public static  final int TYPE_FORGOT_PASSWORD =3;
    //将要添加MainFragment
    public static  final int TYPE_MAIN_FRAGMENT = 4;

    private int type;
    private String fragmentFullName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFragmentFullName() {
        return fragmentFullName;
    }

    public void setFragmentFullName(String fragmentFullName) {
        this.fragmentFullName = fragmentFullName;
    }

    public MessageEvent(int type, String fragmentFullName) {
        this.type = type;
        this.fragmentFullName = fragmentFullName;
    }

    public MessageEvent() {
    }
}
