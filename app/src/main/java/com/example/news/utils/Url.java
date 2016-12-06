package com.example.news.utils;

/**
 * Created by 86409 on 2016/11/28.
 */

public class Url {

    public static final String BASE_URL = "http://118.244.212.82:9092/newsClient";
    //获取新闻分类接口地址
    public static final String GET_NETS_TYPE = BASE_URL + "/news_sort";

    public static  final String GET_NEWS = BASE_URL + "/news_list";

    public static  final  String GET_CONNECT_NUM = BASE_URL + "/cmt_num";

    public static final String REGISITER = BASE_URL + "/user_register";

    public static final String FORGET_PASSWORD = BASE_URL + "/user_forgetpass";

    public static final String LOGIN = BASE_URL + "/user_login";

    public static final String COMMENT = BASE_URL + "/cmt_list";

    public static final String CMT_COMMENT = BASE_URL + "/cmt_commit";
}
