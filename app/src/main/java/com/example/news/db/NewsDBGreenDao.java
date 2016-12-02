package com.example.news.db;

import android.content.Context;

import com.example.news.gdentity.DaoMaster;
import com.example.news.gdentity.DaoSession;
import com.example.news.gdentity.GdNewsTypeDao;

import org.greenrobot.greendao.AbstractDaoMaster;

import java.util.List;

/**
 * Created by 86409 on 2016/12/2.
 */

public class NewsDBGreenDao {

    private Context context;

    public NewsDBGreenDao(Context context){
        this.context = context;
    }

    public  void quary(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"News.db");
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        GdNewsTypeDao dao = daoSession.getGdNewsTypeDao();

        List list = dao.loadAll();


    }





}
