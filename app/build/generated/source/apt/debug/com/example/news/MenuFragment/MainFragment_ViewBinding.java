// Generated code from Butter Knife. Do not modify!
package com.example.news.MenuFragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.news.R;
import com.example.news.view.HorizontalListView;
import com.example.news.xListView.XListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding<T extends MainFragment> implements Unbinder {
  protected T target;

  @UiThread
  public MainFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.hlType = Utils.findRequiredViewAsType(source, R.id.hl_type, "field 'hlType'", HorizontalListView.class);
    target.ivTypeMore = Utils.findRequiredViewAsType(source, R.id.iv_typemore, "field 'ivTypeMore'", ImageView.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", XListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.hlType = null;
    target.ivTypeMore = null;
    target.listView = null;

    this.target = null;
  }
}
