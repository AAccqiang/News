package com.example.news.activity;

import com.example.news.MenuFragment.ForgetPasswordFragment;
import com.example.news.MenuFragment.LeftMenuFragment;
import com.example.news.MenuFragment.LoginFragment;
import com.example.news.MenuFragment.MainFragment;
import com.example.news.MenuFragment.RegisterFragment;
import com.example.news.MenuFragment.RightMenuFragment;
import com.example.news.R;
import com.example.news.base.BaseActivity;
import com.example.news.entity.MessageEvent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {
	private ImageView imHeadLeft;
	private TextView tvHeadCenter;
	private ImageView ImHeadRight;

	private Fragment leftMenuFragment;
	private Fragment rightMenuFragment;
	public   static  SlidingMenu slidingMenu;

	private long prevTime;


	private TextView tvTitle;

	private MainFragment mainFragment;
	private LoginFragment loginFragment;
	private RegisterFragment registerFragment;
	private ForgetPasswordFragment forgetPasswordFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
		initSlidingmenulibary();
		imHeadLeft.setOnClickListener(listener);
		ImHeadRight.setOnClickListener(listener);
		initMain();
	}

	public void initMain(){
		if(mainFragment == null){
			mainFragment = new MainFragment();
		}
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.rl_content,mainFragment);
		ft.commit();


	}


	private void twiceExit(){
		long currentTime = System.currentTimeMillis();
		if(currentTime - prevTime > 1500){
			Toast.makeText(HomeActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
			prevTime = currentTime;
		}else{
			prevTime = currentTime;
			finish();
			System.exit(0);
		}
	}


	//当按下back键时会调用该方法
	@Override
	public void onBackPressed() {
		if(slidingMenu != null && slidingMenu.isMenuShowing()){
			slidingMenu.showContent();
		}else{
			twiceExit();
		}

	}

	private void initSlidingmenulibary(){
		leftMenuFragment = new LeftMenuFragment();
		rightMenuFragment = new RightMenuFragment();
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindWidthRes(R.dimen.sliding_menu_margin);
		slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

		slidingMenu.setMenu(R.layout.layout_left_menu);
		slidingMenu.setSecondaryMenu(R.layout.layout_right_menu);

		getSupportFragmentManager().beginTransaction().replace(R.id.ll_left_container,leftMenuFragment).
				replace(R.id.ll_right_container,rightMenuFragment).commit();
	}

	private void  init(){
		imHeadLeft = (ImageView) findViewById(R.id.im_head_left);

		ImHeadRight = (ImageView) findViewById(R.id.im_head_right);
		tvTitle = (TextView) findViewById(R.id.tv_title);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void switchFragment(MessageEvent event){
		String title = "";
		switch (event.getType()){
			case MessageEvent.TYPE_FORGOT_PASSWORD:
				if (forgetPasswordFragment == null){
					forgetPasswordFragment = new ForgetPasswordFragment();
				}
				title = "忘记密码";
				getSupportFragmentManager().beginTransaction().replace(R.id.rl_content,forgetPasswordFragment).commit();
				break;
			case MessageEvent.TYPE_LOGIN_FRAGMENT:
				if(loginFragment == null){
					loginFragment = new LoginFragment();
				}
				title = "用户登陆";
				getSupportFragmentManager().beginTransaction().replace(R.id.rl_content,loginFragment).commit();

				break;
			case MessageEvent.TYPE_MAIN_FRAGMENT:
				if(mainFragment == null){
					mainFragment = new MainFragment();
				}
				title = "资讯";
				getSupportFragmentManager().beginTransaction().replace(R.id.rl_content,mainFragment).commit();
				break;
			case MessageEvent.TYPE_REGISTER_FRAGMENt:

				if(registerFragment == null){
					registerFragment = new RegisterFragment();
				}
				title = "用户注册";
				getSupportFragmentManager().beginTransaction().replace(R.id.rl_content,registerFragment).commit();
				break;

		}
		tvTitle.setText(title);
		if(slidingMenu != null && slidingMenu.isMenuShowing()){
			slidingMenu.showContent();
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);

	}

	@Override
	protected void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()){
				case R.id.im_head_left:
					if(slidingMenu != null && slidingMenu.isMenuShowing()){
						slidingMenu.showContent();
					}else if(slidingMenu != null){
						slidingMenu.showMenu();
					}
					break;
				case R.id.im_head_right:
					if(slidingMenu != null && slidingMenu.isMenuShowing()){
						slidingMenu.showContent();
					}else if(slidingMenu != null){
						slidingMenu.showSecondaryMenu();
					}
					break;

			}
		}
	};
	
}
