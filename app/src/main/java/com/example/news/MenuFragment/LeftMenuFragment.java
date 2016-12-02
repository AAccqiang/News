package com.example.news.MenuFragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 86409 on 2016/11/23.
 */

public class LeftMenuFragment extends Fragment {
    private RelativeLayout rlNews;
    private RelativeLayout rlFavorite;
    private RelativeLayout rlLocal;
    private RelativeLayout rlComment;
    private RelativeLayout rlPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left,container,false);
        rlNews = (RelativeLayout) view.findViewById(R.id.ToNews);
        rlFavorite = (RelativeLayout) view.findViewById(R.id.ToFavorite);
        rlLocal = (RelativeLayout) view.findViewById(R.id.ToLocat);
        rlComment = (RelativeLayout) view.findViewById(R.id.ToComment);
        rlPhone = (RelativeLayout) view.findViewById(R.id.ToPhone);

        rlNews.setOnClickListener(listener);
        rlFavorite.setOnClickListener(listener);
        rlLocal.setOnClickListener(listener);
        rlComment.setOnClickListener(listener);
        rlPhone.setOnClickListener(listener);


        return view;
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rlNews.setBackgroundColor(0);
            rlFavorite.setBackgroundColor(0);
            rlLocal.setBackgroundColor(0);
            rlComment.setBackgroundColor(0);
            rlPhone.setBackgroundColor(0);

            switch (v.getId()) {
                case R.id.ToNews:
                    rlNews.setBackgroundColor(getResources().getColor(R.color.fragment_left_color));
                    MessageEvent event = new MessageEvent();
                    event.setType(MessageEvent.TYPE_MAIN_FRAGMENT);
                    event.setFragmentFullName(MainFragment.class.getName());
                    EventBus.getDefault().post(event);
                    Toast.makeText(getActivity(),"新闻",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ToFavorite:
                    rlFavorite.setBackgroundColor(getResources().getColor(R.color.fragment_left_color));
                    Toast.makeText(getActivity(),"收藏",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ToLocat:
                    rlLocal.setBackgroundColor(getResources().getColor(R.color.fragment_left_color));
                    Toast.makeText(getActivity(),"本地",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ToComment:
                    rlComment.setBackgroundColor(getResources().getColor(R.color.fragment_left_color));
                    Toast.makeText(getActivity(),"跟帖",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ToPhone:
                    rlPhone.setBackgroundColor(getResources().getColor(R.color.fragment_left_color));
                    Toast.makeText(getActivity(),"图片",Toast.LENGTH_SHORT).show();
                    break;
                 }
        }
    };


}
