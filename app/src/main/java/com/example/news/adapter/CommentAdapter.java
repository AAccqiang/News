package com.example.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.BaseActivity;
import com.example.news.entity.CommentEntity;
import com.example.news.utils.LoadImage;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 86409 on 2016/12/6.
 */

public class CommentAdapter extends BaseAdapter {

    private List<CommentEntity> list = new ArrayList<CommentEntity>();
    private Context context;
    private LayoutInflater inflater;
    private LoadImage loadImage;

    public  CommentAdapter(Context context){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loadImage = new LoadImage(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public List<CommentEntity> getData(){
        return  list;
    }


    public void appendDataToAdapter(List<CommentEntity> data, boolean isClear) {
        if (data == null || data.size() == 0) {
            return;
        }

        if (isClear) {
            list.clear();
        }
        list.addAll(data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.news_comment_item,null);
           //ButterKnife.bind(holder,view);
            holder.iv_comment_img = (ImageView) view.findViewById(R.id.iv_comment_img);
            holder.tv_comment_content = (TextView) view.findViewById(R.id.tv_comment_content);
            holder.tv_comment_stamp = (TextView) view.findViewById(R.id.tv_comment_stamp);
            holder.tv_comment_uid = (TextView) view.findViewById(R.id.tv_comment_uid);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        CommentEntity commentEntity = list.get(i);
        loadImage.displayBitmap(commentEntity.getPortrait(), holder.iv_comment_img);
      //  holder.iv_comment_img.setImageResource(R.drawable.biz_pc_main_info_profile_avatar_bg_dark);
        holder.tv_comment_uid.setText(commentEntity.getUid());
        holder.tv_comment_stamp.setText(commentEntity.getStamp());
        holder.tv_comment_content.setText(commentEntity.getContent());
        return view;
    }

    class ViewHolder{
     //   @BindView(R.id.iv_comment_img)
        ImageView iv_comment_img;
       // @BindView(R.id.tv_comment_uid)
        TextView tv_comment_uid;
      //  @BindView(R.id.tv_comment_stamp)
        TextView tv_comment_stamp;
      //  @BindView(R.id.tv_comment_content)
        TextView tv_comment_content;

    }

}
