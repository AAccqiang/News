package com.example.news.activity;

 
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.adapter.LeadPagerAdapter;

public class LeadActivity extends Activity{

	private ViewPager viewPager;
	private ImageView[] imageViews = new ImageView[4];
	
	private int[] imageArray = {
			R.drawable.welcome,R.drawable.wy,R.drawable.bd,R.drawable.small
	};
	private List<View> viewList = new ArrayList<View>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences sp = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
		boolean isFirstRun = sp.getBoolean("isFirstRun", true);
		if(isFirstRun){
			save();
			setContentView(R.layout.activity_main);
			init();
			initViewPager();
			
		}else {
			toLogo();
		}
	}
	
 
	
	private void toLogo() {
		Intent intent = new Intent(this,LogoActivity.class);
		overridePendingTransition(R.anim.right_in, R.anim.bottom_out);
		startActivity(intent);
		finish();
	}

	public void save(){
		SharedPreferences sp = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean("isFirstRun", false);
		editor.commit();
	}
	
	private void init(){
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		imageViews[0] = (ImageView) findViewById(R.id.imageView1);
		imageViews[1] = (ImageView) findViewById(R.id.imageView2);
		imageViews[2] = (ImageView) findViewById(R.id.imageView3);
		imageViews[3] = (ImageView) findViewById(R.id.imageView4);
	}


	private void inits(){
		for(int i = 0;i < imageArray.length;i++){
			ImageView lead_imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_lead, null);
			lead_imageView.setImageResource(imageArray[i]);
			viewList.add(lead_imageView);
		}
	}
	
	private void initViewPager(){
		for(int i = 0;i < imageArray.length;i++){
			ImageView lead_imageview= (ImageView) getLayoutInflater().inflate(R.layout.activity_lead, null);
			lead_imageview.setImageResource(imageArray[i]);
			viewList.add(lead_imageview);
		}
		
		LeadPagerAdapter pa = new LeadPagerAdapter(this, viewList);
		viewPager.setAdapter(pa);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for(int i = 0;i < imageArray.length;i++){
					imageViews[i].setAlpha(128);
					 
				}
				imageViews[arg0].setAlpha(255);
				if(arg0 == 3){
					toLogo();
				}
				 
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {		
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {			
			}
		});
		
		 
		
	}
}
