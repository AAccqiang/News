// Generated code from Butter Knife. Do not modify!
package com.example.news.MenuFragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RightMenuFragment_ViewBinding<T extends RightMenuFragment> implements Unbinder {
  protected T target;

  private View view2131361834;

  private View view2131361835;

  @UiThread
  public RightMenuFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.llUnLogin = Utils.findRequiredViewAsType(source, R.id.ll_unlogin, "field 'llUnLogin'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.im_right_unlogin, "field 'ivUnLogin' and method 'publishAddLoginFragmentEvent'");
    target.ivUnLogin = Utils.castView(view, R.id.im_right_unlogin, "field 'ivUnLogin'", ImageView.class);
    view2131361834 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.publishAddLoginFragmentEvent();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_right_unlogin, "field 'tvUnLogin' and method 'publistAddLoginFragmentEvent_2'");
    target.tvUnLogin = Utils.castView(view, R.id.tv_right_unlogin, "field 'tvUnLogin'", TextView.class);
    view2131361835 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.publistAddLoginFragmentEvent_2();
      }
    });
    target.llLogin = Utils.findRequiredViewAsType(source, R.id.ll_login, "field 'llLogin'", LinearLayout.class);
    target.ivLogin = Utils.findRequiredViewAsType(source, R.id.im_right_login, "field 'ivLogin'", ImageView.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tv_right_login, "field 'tvLogin'", TextView.class);
    target.ivWeixin = Utils.findRequiredViewAsType(source, R.id.iv_weixin, "field 'ivWeixin'", ImageView.class);
    target.ivQQ = Utils.findRequiredViewAsType(source, R.id.iv_qq, "field 'ivQQ'", ImageView.class);
    target.ivFriend = Utils.findRequiredViewAsType(source, R.id.iv_friend, "field 'ivFriend'", ImageView.class);
    target.ivWeibo = Utils.findRequiredViewAsType(source, R.id.iv_weibo, "field 'ivWeibo'", ImageView.class);
    target.tvUpdateVersion = Utils.findRequiredViewAsType(source, R.id.tv_update_version, "field 'tvUpdateVersion'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llUnLogin = null;
    target.ivUnLogin = null;
    target.tvUnLogin = null;
    target.llLogin = null;
    target.ivLogin = null;
    target.tvLogin = null;
    target.ivWeixin = null;
    target.ivQQ = null;
    target.ivFriend = null;
    target.ivWeibo = null;
    target.tvUpdateVersion = null;

    view2131361834.setOnClickListener(null);
    view2131361834 = null;
    view2131361835.setOnClickListener(null);
    view2131361835 = null;

    this.target = null;
  }
}
