package com.example.news.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.adapter.LoginAdapter;
import com.example.news.base.BaseActivity;
import com.example.news.biz.UserManager;
import com.example.news.entity.BaseEntity;
import com.example.news.entity.LoginLog;
import com.example.news.entity.User;
import com.example.news.entity.UserResponse;
import com.example.news.listener.FailListener;
import com.example.news.listener.SuccessListener;
import com.example.news.parser.UserParser;
import com.example.news.utils.CommonUtils;
import com.example.news.utils.LoadImage;
import com.example.news.utils.OkHttpUtils;
import com.example.news.utils.SharedPreferencesUtils;
import com.example.news.utils.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 86409 on 2016/12/5.
 */

public class UserActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout llContaioner;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_num)
    TextView tv_Integral;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    LoadImage loadImage;
    LoginAdapter loginAdapter;
    @BindView(R.id.listView)
    ListView listView;

    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        loadImage = new LoadImage(this);

        SharedPreferences sp = getSharedPreferences("users",Context.MODE_PRIVATE);
        String username = sp.getString("name","");
        String headImage = sp.getString("headImage","");
        String localHeadImage = sp.getString("localHeadImage","");
        tvName.setText(username);
        File file = new File(localHeadImage);
        if(file.exists()){

            ivIcon.setImageURI(Uri.fromFile(file));
        }else{

            loadImage.displayBitmap(headImage,ivIcon);
        }

        loginAdapter = new LoginAdapter(this);
        senRequest();
        listView.setAdapter(loginAdapter);
        initListener();
        initPoputWindow();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initPoputWindow(){
        View view = getLayoutInflater().inflate(R.layout.popup_phone_layout,null);
        LinearLayout llTakePhoto = (LinearLayout) view.findViewById(R.id.ll_take_photo);
        LinearLayout llSelectPhote = (LinearLayout) view.findViewById(R.id.ll_sel_photo);

        llTakePhoto.setOnClickListener(listener);
        llSelectPhote.setOnClickListener(listener);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
    }

    @OnClick(R.id.btn_logout)
    public void logout(){
        SharedPreferencesUtils.CleanData(this);
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    User user = null;
    private void senRequest(){
        UserManager.getUserInfo(this, new SuccessListener() {
            @Override
            public void onSuccess(String json) {
                System.out.println(json + "22222222222222222");
                BaseEntity<User> baseEntity = UserParser.parseUser(json);
                if(baseEntity.getStatus().equals("0")){
                     user = baseEntity.getData();

                    tvName.setText(user.getUid());
                    tv_Integral.setText(user.getIntegration()+"");
                    tvCommentCount.setText(user.getComnum() + "");

                    if(!TextUtils.isEmpty(user.getPortrait())){
                         loadImage.displayBitmap(user.getPortrait(),ivIcon);
                    }
                    SharedPreferencesUtils.saveUser(UserActivity.this,user.getUid(),user.getPortrait());
                    List<LoginLog> loginLogList = user.getLoginlog();
                    loginAdapter.appendDataToAdapter(loginLogList);
                }

            }
        }, new FailListener() {
            @Override
            public void onFail(String error) {

            }
        });
    }



    @OnClick(R.id.im_head_left)
    public void back(){

        finish();
    }

    private void initListener(){
        ivIcon.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.iv_icon:
                    popupWindow.showAtLocation(llContaioner, Gravity.BOTTOM,0,0);
                    break;
                case R.id.ll_take_photo:
                    popupWindow.dismiss();
                    takePhoto();
                    break;
                case  R.id.ll_sel_photo:
                    popupWindow.dismiss();
                    selectPhoto();
                    break;
            }
        }
    };

    Bitmap bitmap;

    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);
    }

    private void selectPhoto(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,200);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 100){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = intent.getExtras();
                bitmap = (Bitmap) bundle.get("data");
//                save(bitmap);
            }
        }else if(requestCode == 200){
            if(resultCode == Activity.RESULT_OK){
                Uri originalUri = intent.getData();        //获得图片的uri

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), originalUri);
                    //ivIcon.setImageBitmap(bitmap);
                    save(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    File cacheDir = null;
    private void save(Bitmap bitmap) {
         cacheDir = new File(getExternalCacheDir(), "newsClient");
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }

        File file = new File(cacheDir, "headImage.jpg");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)){
                //发送网络请求上传头像
                String token = SharedPreferencesUtils.getToken(UserActivity.this);
                OkHttpUtils.updateFile(Url.USER_IMAGE + "?token=" + token,token,file,successListener,failListener);
              //  SharedPreferencesUtils.saveUserHeadImagePath(UserActivity.this,file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private SuccessListener successListener = new SuccessListener() {
        @Override
        public void onSuccess(String json) {
            Gson gson = new Gson();
            BaseEntity<UserResponse> baseEntity = gson.fromJson(json,new TypeToken<BaseEntity<UserResponse>>(){}.getType());
            if(baseEntity.getStatus().equals("0")){
                if(baseEntity.getData().getResult() == 0){
                    ivIcon.setImageBitmap(bitmap);
                    File file = new File(cacheDir,"headImage.jpg");
                    SharedPreferencesUtils.saveUserHeadImagePath(UserActivity.this,file.getPath());
                    return;
                }
            }
            CommonUtils.showShort(UserActivity.this,"上传头像失败，请重试");

        }

    };

    private FailListener failListener = new FailListener() {
        @Override
        public void onFail(String error) {
            CommonUtils.showShort(UserActivity.this,"上传头像失败，请重试");

        }
    };

    private Bitmap counvertRoundBitmap(){
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.userbg);
        Bitmap dest = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(),srcBitmap.getConfig());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(bitmap,0,0,paint);
        return bitmap;
    }

}
