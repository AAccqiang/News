// Generated code from Butter Knife. Do not modify!
package com.example.news.MenuFragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPasswordFragment_ViewBinding<T extends ForgetPasswordFragment> implements Unbinder {
  protected T target;

  private View view2131361817;

  @UiThread
  public ForgetPasswordFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'etEmail'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_confirm, "field 'btnConfirm' and method 'findPassword'");
    target.btnConfirm = Utils.castView(view, R.id.btn_confirm, "field 'btnConfirm'", Button.class);
    view2131361817 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.findPassword();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etEmail = null;
    target.btnConfirm = null;

    view2131361817.setOnClickListener(null);
    view2131361817 = null;

    this.target = null;
  }
}
