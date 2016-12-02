// Generated code from Butter Knife. Do not modify!
package com.example.news.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowNewsActivity_ViewBinding<T extends ShowNewsActivity> implements Unbinder {
  protected T target;

  private View view2131361801;

  private View view2131361802;

  private View view2131361803;

  @UiThread
  public ShowNewsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'back'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131361801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_menu, "field 'ivMenu' and method 'showMenu'");
    target.ivMenu = Utils.castView(view, R.id.iv_menu, "field 'ivMenu'", ImageView.class);
    view2131361802 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showMenu();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_comment_num, "field 'tvComment_num' and method 'gotoComment'");
    target.tvComment_num = Utils.castView(view, R.id.tv_comment_num, "field 'tvComment_num'", TextView.class);
    view2131361803 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.gotoComment();
      }
    });
    target.webView = Utils.findRequiredViewAsType(source, R.id.webView, "field 'webView'", WebView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivBack = null;
    target.ivMenu = null;
    target.tvComment_num = null;
    target.webView = null;
    target.progressBar = null;

    view2131361801.setOnClickListener(null);
    view2131361801 = null;
    view2131361802.setOnClickListener(null);
    view2131361802 = null;
    view2131361803.setOnClickListener(null);
    view2131361803 = null;

    this.target = null;
  }
}
