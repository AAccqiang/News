package com.example.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.LoginLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 86409 on 2016/12/7.
 */

public class LoginAdapter extends BaseAdapter {

    private List<LoginLog> list = new ArrayList<LoginLog>();
    private Context context;
    private LayoutInflater inflater;

    public LoginAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public  void  appendDataToAdapter(List<LoginLog> dataList){
        if(dataList == null || dataList.size() == 0){
            return;
        }
        list.clear();
        list.addAll(dataList);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder ;
        if(view == null){
            view = inflater.inflate(R.layout.loginlog_list_item,null);
            holder = new ViewHolder();
            ButterKnife.bind(holder,view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        LoginLog log = list.get(i);
        holder.tvAddress.setText(log.getAddress());
        holder.tvDevice.setText(log.getDevice() == 0 ? "手机客户端" :"pc客户端");

        holder.tvTime.setText(log.getTime().substring(0,10));
        return  view;
    }

    class ViewHolder{
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_adress)
        TextView tvAddress;
        @BindView(R.id.tv_dvice)
        TextView tvDevice;
    }
}
