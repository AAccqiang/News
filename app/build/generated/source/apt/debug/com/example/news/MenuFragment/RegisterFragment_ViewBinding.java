// Generated code from Butter Knife. Do not modify!
package com.example.news.MenuFragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.news.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterFragment_ViewBinding<T extends RegisterFragment> implements Unbinder {
  protected T target;

  private View view2131361835;

  @UiThread
  public RegisterFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'etEmail'", EditText.class);
    target.etNickname = Utils.findRequiredViewAsType(source, R.id.et_nickname, "field 'etNickname'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_register, "field 'btnRegister' and method 'register'");
    target.btnRegister = Utils.castView(view, R.id.btn_register, "field 'btnRegister'", Button.class);
    view2131361835 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.register();
      }
    });
    target.cbAgree = Utils.findRequiredViewAsType(source, R.id.cb_agree, "field 'cbAgree'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etEmail = null;
    target.etNickname = null;
    target.etPassword = null;
    target.btnRegister = null;
    target.cbAgree = null;

    view2131361835.setOnClickListener(null);
    view2131361835 = null;

    this.target = null;
  }
}
