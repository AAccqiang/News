package com.example.news.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.news.entity.News;
import com.example.news.entity.SubType;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class NewsDBManager {
    private NewsDBHelper helper;
    private Context context;


    public NewsDBManager(Context context) {
        this.context = context;
        this.helper = new NewsDBHelper(context);
    }

    public List<SubType> getNewsSubType( ){


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news_type order by subid",null);
        List<SubType> list = new ArrayList<SubType>();
        while (cursor.moveToNext()){
            int subid = cursor.getInt(cursor.getColumnIndex("subid"));
            String subgroup = cursor.getString(cursor.getColumnIndex("subgroup"));
            SubType subType = new SubType(subid,subgroup);
            list.add(subType);

        }
        cursor.close();
        cursor = null;
        return list;

    }


    //保存新闻分类数据
    public void saveNewsType(List<SubType> list){
        if(list == null || list.size() == 0){
            return;
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        for(int i = 0;i < list.size();i++){
            SubType subType = list.get(i);
            Cursor cursor = db.rawQuery("select * from news_type where subid = ?",new String[]{subType.getSubid() + ""});
            if(cursor.moveToFirst()){
                cursor.close();
                return;
            }
            cursor.close();

            //插入数据
            ContentValues values = new ContentValues();
            values.put("subid",subType.getSubid());
            values.put("subgroup",subType.getSubgroup());

            db.insert("news_type",null,values);

        }
        db.close();
    }

    //查询指定的新闻是否已收藏
    public boolean isCollectNews(News news){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from lovenews  where nid = ?",new String[]{String.valueOf(news.getNid())});
        boolean isExists = false;

        if(cursor.moveToFirst()){
            isExists = true;
        }
        cursor.close();
        return isExists;
    }

    //保存新闻数据
    public void save(News news){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nid",news.getNid());
        values.put("stamp",news.getStamp());
        values.put("icon",news.getIcon());
        values.put("title",news.getTitle());
        values.put("summary",news.getSummary());
        values.put("link",news.getLink());
        db.insert("lovenews",null,values);

    }

}
