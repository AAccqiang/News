package com.example.news.utils;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by 86409 on 2016/11/29.
 */

public class LoadImage {
    private Context context;
    private LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(4 * 1024 * 1024);

    public  LoadImage(Context context){
        this.context = context;

    }
    //url图片路径，imageView 用于将路径显示图片
    public  void  displayBitmap(String url, ImageView imageView){
        //判断路径是否合法
        if(TextUtils.isEmpty(url)){
            return;
        }

        //先从内存中的缓存中查看是否有缓存该图片，如果有，则使用缓存中的图片，如果没有，则继续后面的操作
        Bitmap bitmap = cache.get(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        //如果内存中没有缓存该图片，则区缓存的我文件中查找是否有该图片，如果有，则显示该图片
        // 如果没有，则继续后面的操作（网络上加载图片）

        bitmap = getBitmapFromFileCahe(url);
        if(bitmap != null){
            cache.put(url,bitmap);
            imageView.setImageBitmap(bitmap);
            return;
        }

        //如果文件缓存中也没有对应的图片，则从网络进行加载图片，并缓存中文件与内存中
        VolleyHttp volleyHttp = new VolleyHttp(context);
        volleyHttp.sendImageViewRequest(url,imageCache,imageView);
    }
    private ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache(){

        @Override
        public Bitmap getBitmap(String s) {
            return cache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            cache.put(s,bitmap);
            saveBitmapToFileCache(s,bitmap);
        }
    };


    private  void  saveBitmapToFileCache(String url,Bitmap bitmap){
        String filename = url.substring(url.lastIndexOf("/") + 1);
        File cacheDir = context.getExternalCacheDir();

        //如果返回null,则不进行缓存
        if((cacheDir == null)){
            return;
        }

        //如果不为空，但目录还未创建，则创建该目录
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        try {
            OutputStream stream = new FileOutputStream(new File(cacheDir,filename));
            //1)图片类型，2）图片质量，3）f输出流图片会通常会通过输出流保存至文件中
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private  Bitmap getBitmapFromFileCahe(String url){
        String filename = url.substring(url.lastIndexOf("/") + 1);
        //获取缓存文件目录  //waibu
        File cacheDir = context.getExternalCacheDir();

        if(cacheDir != null && cacheDir.exists()){
            File bitmapFile = new File(cacheDir,filename);
            //判断文件是否存在，如果存在，则进行加载，并返回，如果不存在，则返回null
            if(bitmapFile != null && cacheDir.exists()){
                return BitmapFactory.decodeFile(bitmapFile.getAbsolutePath());
            }
        }
        return null;
    }

}
