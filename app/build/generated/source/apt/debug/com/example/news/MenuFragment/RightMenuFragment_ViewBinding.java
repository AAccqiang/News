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

  private View view2131361843;

  private View view2131361844;

  private View view2131361846;

  private View view2131361847;

  private View view2131361848;

  @UiThread
  public RightMenuFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.llUnLogin = Utils.findRequiredViewAsType(source, R.id.ll_unlogin, "field 'llUnLogin'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.im_right_unlogin, "field 'ivUnLogin' and method 'publishAddLoginFragmentEvent'");
    target.ivUnLogin = Utils.castView(view, R.id.im_right_unlogin, "field 'ivUnLogin'", ImageView.class);
    view2131361843 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.publishAddLoginFragmentEvent();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_right_unlogin, "field 'tvUnLogin' and method 'publistAddLoginFragmentEvent_2'");
    target.tvUnLogin = Utils.castView(view, R.id.tv_right_unlogin, "field 'tvUnLogin'", TextView.class);
    view2131361844 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.publistAddLoginFragmentEvent_2();
      }
    });
    target.llLogin = Utils.findRequiredViewAsType(source, R.id.ll_login, "field 'llLogin'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.im_right_login, "field 'ivLogin' and method 'toUser'");
    target.ivLogin = Utils.castView(view, R.id.im_right_login, "field 'ivLogin'", ImageView.class);
    view2131361846 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toUser();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_right_login, "field 'tvLogin' and method 'toUser2'");
    target.tvLogin = Utils.castView(view, R.id.tv_right_login, "field 'tvLogin'", TextView.class);
    view2131361847 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toUser2();
      }
    });
    target.ivWeixin = Utils.findRequiredViewAsType(source, R.id.iv_weixin, "field 'ivWeixin'", ImageView.class);
    target.ivQQ = Utils.findRequiredViewAsType(source, R.id.iv_qq, "field 'ivQQ'", ImageView.class);
    target.ivFriend = Utils.findRequiredViewAsType(source, R.id.iv_friend, "field 'ivFriend'", ImageView.class);
    target.ivWeibo = Utils.findRequiredViewAsType(source, R.id.iv_weibo, "field 'ivWeibo'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_update_version, "field 'tvUpdateVersion' and method 'getUpdateVersion'");
    target.tvUpdateVersion = Utils.castView(view, R.id.tv_update_version, "field 'tvUpdateVersion'", TextView.class);
    view2131361848 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getUpdateVersion();
      }
    });
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

    view2131361843.setOnClickListener(null);
    view2131361843 = null;
    view2131361844.setOnClickListener(null);
    view2131361844 = null;
    view2131361846.setOnClickListener(null);
    view2131361846 = null;
    view2131361847.setOnClickListener(null);
    view2131361847 = null;
    view2131361848.setOnClickListener(null);
    view2131361848 = null;

    this.target = null;
  }
}
