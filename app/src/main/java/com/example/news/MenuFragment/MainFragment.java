package com.example.news.MenuFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.activity.ShowNewsActivity;
import com.example.news.adapter.NewsAdapter;
import com.example.news.adapter.NewsTypeAdapter;
import com.example.news.base.BaseActivity;
import com.example.news.biz.NewsManager;
import com.example.news.db.NewsDBGreenDao;
import com.example.news.db.NewsDBManager;
import com.example.news.entity.News;
import com.example.news.entity.SubType;
import com.example.news.gdentity.GdNewsType;
import com.example.news.parser.NewsParser;
import com.example.news.utils.CommonUtils;
import com.example.news.view.HorizontalListView;
import com.example.news.xListView.XListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment {

    private NewsDBManager newsDBManager;
    private NewsTypeAdapter adapter;

    @BindView(R.id.hl_type)
    HorizontalListView hlType;
    @BindView(R.id.iv_typemore)
    ImageView ivTypeMore;
    @BindView(R.id.listView)
    XListView listView;
    //分类编号
    private int subid = 1;
    //加载数据模式
    private int refreshMode = 1;

    private NewsAdapter newsAdapter;
    private NewsDBGreenDao newsDBGreenDao;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,v);

        adapter = new NewsTypeAdapter(getActivity());
        newsDBManager = new NewsDBManager(getActivity());
        newsDBGreenDao = new NewsDBGreenDao(getActivity());


        hlType.setOnItemClickListener(itemClickListener);
        hlType.setAdapter(adapter);
        loadNewsType();

        newsAdapter = new NewsAdapter(getActivity());
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        listView.setXListViewListener(listViewListener);
        listView.setOnItemClickListener(newsItemClickListener);
        listView.setAdapter(newsAdapter);



        refreshMode = NewsManager.MODE_PULL_REFRESH;
        loadNewxNews(true);
        ((BaseActivity)getActivity()).showDialog(null,false);
        return v;
    }

    private XListView.IXListViewListener listViewListener = new XListView.IXListViewListener() {
        //下拉刷新会回调该方法
        @Override
        public void onRefresh() {
            refreshMode = NewsManager.MODE_PULL_REFRESH;
            loadNewxNews(false);
        }

        //上啦刷新会回调该方法
        @Override
        public void onLoadMore() {
            refreshMode = NewsManager.MODE_LOAD_MORE;
            loadPrevNews();
        }
    };

    private void loadNewxNews(boolean isNewType){
        int nid = 1;
        if(!isNewType){
            if(newsAdapter.getData().size() > 0){
                nid = newsAdapter.getData().get(0).getNid();
            }
        }

        if(CommonUtils.isNewConnect(getActivity())){
            NewsManager.getNewsList(getActivity(),subid,refreshMode,nid,newlistener,errorListener);
        }else{

        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            SubType subType = (SubType) adapter.getItem(i);
            subid = subType.getSubid();
            adapter.setCurrentPosition(i);
            adapter.notifyDataSetChanged();
            ((BaseActivity)getActivity()).showDialog(null,false);
            refreshMode = NewsManager.MODE_PULL_REFRESH;
            loadNewxNews(true);
        }
    };

    private AdapterView.OnItemClickListener newsItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            News news = (News) adapterView.getItemAtPosition(i);
          //  News news = newsAdapter.getData().get(i);
            Bundle bundle = new Bundle();

            bundle.putSerializable("news",news);
            BaseActivity activity = (BaseActivity) getActivity();
            activity.startActivity(ShowNewsActivity.class,bundle);
        }
    };



    private void loadPrevNews(){
        if(newsAdapter.getData().size() == 0){return;}
        int lastIndex = newsAdapter.getData().size() - 1;
        int nid = newsAdapter.getData().get(lastIndex).getNid();
        System.out.println(nid + "vvvvvvvvvvvvvvvvvv");

        if(CommonUtils.isNewConnect(getActivity())){
            NewsManager.getNewsList(getActivity(),subid,refreshMode,nid,newlistener,errorListener);
        }else{

        }
    }



    //加载新闻分类
    private void loadNewsType(){
        //选判断数据库中是否有缓存数据，如果有，则使用缓存的数据，如果没有，则判断是否有网络，则去服务器端加载数据
        if(newsDBManager.getNewsSubType().size() == 0){
            if(CommonUtils.isNewConnect(getActivity())){
                NewsManager.getNewsType(getActivity(),listener,errorListener);
            }
        }else{
            List<SubType> list = newsDBManager.getNewsSubType();
            adapter.appendDataToAdapter(list,true);
            adapter.notifyDataSetChanged();

        }



    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String json) {
            List<SubType> list = NewsParser.parserNewsType(json);

            Collections.sort(list, new Comparator<SubType>() {
                @Override
                public int compare(SubType subType, SubType t1) {
                    return  subType.getSubid() - t1.getSubid();
                }
            });
            adapter.appendDataToAdapter(list,true);
            adapter.notifyDataSetChanged();
        }
    };
    private Response.Listener<String> newlistener = new Response.Listener<String>() {
        @Override
        public void onResponse(String json) {
            List<News> list = NewsParser.parseNews(json);
            boolean isClear = refreshMode == NewsManager.MODE_PULL_REFRESH ? true : false;
            newsAdapter.appendDataToAdapter(list,isClear);
            newsAdapter.notifyDataSetChanged();
            listView.stopLoadMore();//停止
            listView.stopRefresh();
            listView.setRefreshTime(CommonUtils.getCurrentData());
            ((BaseActivity)getActivity()).cancleDialog();
        }
    };


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //取消进度对话框
            listView.stopLoadMore();//停止
            listView.stopRefresh();
            Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();
        }
    };
}
