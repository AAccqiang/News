// Generated code from Butter Knife. Do not modify!
package com.example.news.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import com.example.news.xListView.XListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentActivity_ViewBinding<T extends CommentActivity> implements Unbinder {
  protected T target;

  private View view2131361792;

  private View view2131361796;

  @UiThread
  public CommentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", XListView.class);
    target.etContent = Utils.findRequiredViewAsType(source, R.id.et_content, "field 'etContent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.im_head_left, "field 'imBack' and method 'back'");
    target.imBack = Utils.castView(view, R.id.im_head_left, "field 'imBack'", ImageView.class);
    view2131361792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_send, "method 'send'");
    view2131361796 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.send();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.listView = null;
    target.etContent = null;
    target.imBack = null;

    view2131361792.setOnClickListener(null);
    view2131361792 = null;
    view2131361796.setOnClickListener(null);
    view2131361796 = null;

    this.target = null;
  }
}
