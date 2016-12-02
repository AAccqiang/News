package com.example.news.base;

import android.app.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.utils.Url;


public class BaseActivity extends FragmentActivity {

    private Dialog dialog;
    public void startActivity(Class<? extends  Activity> clazz){
        startActivity(clazz,null,null);
    }
    public void startActivity(Class<? extends  Activity> clazz, Bundle bundle){
        startActivity(clazz,bundle,null);
    }

    public void startActivity(Class<? extends  Activity> clazz, Bundle bundle, Uri uri){
        Intent intent = new Intent(this,clazz);

        if(bundle != null){
            intent.putExtras(bundle);
        }

        if(uri != null){
            intent.setData(uri);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.right_in,R.anim.bottom_out);
    }

    public void showDialog(String message,boolean isCancel){
        View view = getLayoutInflater().inflate(R.layout.loading,null);
        //旋转动画
        ImageView ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        TextView tvLoading = (TextView) view.findViewById(R.id.tv_loading);

        if(!TextUtils.isEmpty(message)){
            tvLoading.setText(message);
        }
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.loading);
        ivLoading.setAnimation(animation);


        dialog = new Dialog(this,R.style.loading_style);
        dialog.setContentView(view,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        dialog.setCancelable(isCancel);//是否可取消
        dialog.show();
    }

    public  void  cancleDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

}
