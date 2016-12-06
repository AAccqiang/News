// Generated code from Butter Knife. Do not modify!
package com.example.news.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserActivity_ViewBinding<T extends UserActivity> implements Unbinder {
  protected T target;

  private View view2131361810;

  @UiThread
  public UserActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_logout, "field 'btnLogout' and method 'logout'");
    target.btnLogout = Utils.castView(view, R.id.btn_logout, "field 'btnLogout'", Button.class);
    view2131361810 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.logout();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.btnLogout = null;

    view2131361810.setOnClickListener(null);
    view2131361810 = null;

    this.target = null;
  }
}
