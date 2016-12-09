// Generated code from Butter Knife. Do not modify!
package com.example.news.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginAdapter$ViewHolder_ViewBinding<T extends LoginAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public LoginAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_adress, "field 'tvAddress'", TextView.class);
    target.tvDevice = Utils.findRequiredViewAsType(source, R.id.tv_dvice, "field 'tvDevice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvTime = null;
    target.tvAddress = null;
    target.tvDevice = null;

    this.target = null;
  }
}
