package com.example.news.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.news.R;

public class LogoActivity extends Activity{
	private ImageView logoImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		logoImageView = (ImageView) findViewById(R.id.logo_image);
		
		AlphaAnimation ap = new AlphaAnimation(0.1f, 1.0f);
		ap.setFillAfter(true);
		ap.setDuration(3000);
		logoImageView.startAnimation(ap);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LogoActivity.this,HomeActivity.class);
			
				startActivity(intent);
				overridePendingTransition(R.anim.right_in, R.anim.bottom_out);
				finish();
			}
		}, 3000);
	}

}
