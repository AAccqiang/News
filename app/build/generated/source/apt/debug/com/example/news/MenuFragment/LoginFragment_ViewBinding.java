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

public class LoginFragment_ViewBinding<T extends LoginFragment> implements Unbinder {
  protected T target;

  private View view2131361835;

  private View view2131361836;

  private View view2131361837;

  @UiThread
  public LoginFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etNickname = Utils.findRequiredViewAsType(source, R.id.et_nickname, "field 'etNickname'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_register_login, "field 'btnRegister' and method 'publishAddRegisterFragmentEvent'");
    target.btnRegister = Utils.castView(view, R.id.btn_register_login, "field 'btnRegister'", Button.class);
    view2131361835 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.publishAddRegisterFragmentEvent();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_forgot_password, "field 'btnForgotPassword' and method 'publishAddforgotFragmentEvent'");
    target.btnForgotPassword = Utils.castView(view, R.id.btn_forgot_password, "field 'btnForgotPassword'", Button.class);
    view2131361836 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.publishAddforgotFragmentEvent();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btnLogon' and method 'login'");
    target.btnLogon = Utils.castView(view, R.id.btn_login, "field 'btnLogon'", Button.class);
    view2131361837 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etNickname = null;
    target.etPassword = null;
    target.btnRegister = null;
    target.btnForgotPassword = null;
    target.btnLogon = null;

    view2131361835.setOnClickListener(null);
    view2131361835 = null;
    view2131361836.setOnClickListener(null);
    view2131361836 = null;
    view2131361837.setOnClickListener(null);
    view2131361837 = null;

    this.target = null;
  }
}
