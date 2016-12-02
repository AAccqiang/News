package com.example.news.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 86409 on 2016/11/28.
 */

public class NewsDBHelper extends SQLiteOpenHelper{
    public NewsDBHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //用于保存新闻分类的数据
        db.execSQL("create table news_type(_id integer primary key autoIncrement,subid text,subgroup text)");
        //用于保存新闻数据
        db.execSQL("create table news(_id integer primary key autoIncrement,type text,stamp text,icon text" +
                ",summary text,title text,link text)");

        //用户保存收藏新闻数据
        db.execSQL("create table lovenews(_id integer primary key autoIncrement,type text,nid text,stamp text,icon text" +
                ",summary text,title text,link text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
