// Generated code from Butter Knife. Do not modify!
package com.example.news.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserActivity_ViewBinding<T extends UserActivity> implements Unbinder {
  protected T target;

  private View view2131361814;

  private View view2131361792;

  @UiThread
  public UserActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.llContaioner = Utils.findRequiredViewAsType(source, R.id.ll_container, "field 'llContaioner'", LinearLayout.class);
    target.ivIcon = Utils.findRequiredViewAsType(source, R.id.iv_icon, "field 'ivIcon'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tv_Integral = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tv_Integral'", TextView.class);
    target.tvCommentCount = Utils.findRequiredViewAsType(source, R.id.tv_comment_count, "field 'tvCommentCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_logout, "field 'btnLogout' and method 'logout'");
    target.btnLogout = Utils.castView(view, R.id.btn_logout, "field 'btnLogout'", Button.class);
    view2131361814 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.logout();
      }
    });
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", ListView.class);
    view = Utils.findRequiredView(source, R.id.im_head_left, "method 'back'");
    view2131361792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContaioner = null;
    target.ivIcon = null;
    target.tvName = null;
    target.tv_Integral = null;
    target.tvCommentCount = null;
    target.btnLogout = null;
    target.listView = null;

    view2131361814.setOnClickListener(null);
    view2131361814 = null;
    view2131361792.setOnClickListener(null);
    view2131361792 = null;

    this.target = null;
  }
}
