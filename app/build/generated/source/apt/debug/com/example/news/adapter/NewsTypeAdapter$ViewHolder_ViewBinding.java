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

public class NewsTypeAdapter$ViewHolder_ViewBinding<T extends NewsTypeAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public NewsTypeAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tvNewsType = Utils.findRequiredViewAsType(source, R.id.tv_news_type, "field 'tvNewsType'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvNewsType = null;

    this.target = null;
  }
}
