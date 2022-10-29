// Generated by view binder compiler. Do not edit!
package com.muratcakin.poco.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.muratcakin.poco.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnSignInPage;

  @NonNull
  public final MaterialButton btnSignUp;

  @NonNull
  public final Button btnSignUpPage2;

  @NonNull
  public final ImageView ivPoco;

  @NonNull
  public final TextInputEditText textInputEditText;

  @NonNull
  public final TextInputLayout tiConfirmPassword;

  @NonNull
  public final TextInputLayout tiEmailSGnUp;

  @NonNull
  public final TextInputLayout tiPassword;

  @NonNull
  public final TextInputLayout tiUsernameSGnUp;

  @NonNull
  public final TextView tvWelcome;

  private FragmentSignUpBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnSignInPage,
      @NonNull MaterialButton btnSignUp, @NonNull Button btnSignUpPage2, @NonNull ImageView ivPoco,
      @NonNull TextInputEditText textInputEditText, @NonNull TextInputLayout tiConfirmPassword,
      @NonNull TextInputLayout tiEmailSGnUp, @NonNull TextInputLayout tiPassword,
      @NonNull TextInputLayout tiUsernameSGnUp, @NonNull TextView tvWelcome) {
    this.rootView = rootView;
    this.btnSignInPage = btnSignInPage;
    this.btnSignUp = btnSignUp;
    this.btnSignUpPage2 = btnSignUpPage2;
    this.ivPoco = ivPoco;
    this.textInputEditText = textInputEditText;
    this.tiConfirmPassword = tiConfirmPassword;
    this.tiEmailSGnUp = tiEmailSGnUp;
    this.tiPassword = tiPassword;
    this.tiUsernameSGnUp = tiUsernameSGnUp;
    this.tvWelcome = tvWelcome;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSignInPage;
      Button btnSignInPage = ViewBindings.findChildViewById(rootView, id);
      if (btnSignInPage == null) {
        break missingId;
      }

      id = R.id.btnSignUp;
      MaterialButton btnSignUp = ViewBindings.findChildViewById(rootView, id);
      if (btnSignUp == null) {
        break missingId;
      }

      id = R.id.btnSignUpPage2;
      Button btnSignUpPage2 = ViewBindings.findChildViewById(rootView, id);
      if (btnSignUpPage2 == null) {
        break missingId;
      }

      id = R.id.ivPoco;
      ImageView ivPoco = ViewBindings.findChildViewById(rootView, id);
      if (ivPoco == null) {
        break missingId;
      }

      id = R.id.textInputEditText;
      TextInputEditText textInputEditText = ViewBindings.findChildViewById(rootView, id);
      if (textInputEditText == null) {
        break missingId;
      }

      id = R.id.tiConfirmPassword;
      TextInputLayout tiConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (tiConfirmPassword == null) {
        break missingId;
      }

      id = R.id.tiEmailSıgnUp;
      TextInputLayout tiEmailSGnUp = ViewBindings.findChildViewById(rootView, id);
      if (tiEmailSGnUp == null) {
        break missingId;
      }

      id = R.id.tiPassword;
      TextInputLayout tiPassword = ViewBindings.findChildViewById(rootView, id);
      if (tiPassword == null) {
        break missingId;
      }

      id = R.id.tiUsernameSıgnUp;
      TextInputLayout tiUsernameSGnUp = ViewBindings.findChildViewById(rootView, id);
      if (tiUsernameSGnUp == null) {
        break missingId;
      }

      id = R.id.tvWelcome;
      TextView tvWelcome = ViewBindings.findChildViewById(rootView, id);
      if (tvWelcome == null) {
        break missingId;
      }

      return new FragmentSignUpBinding((ConstraintLayout) rootView, btnSignInPage, btnSignUp,
          btnSignUpPage2, ivPoco, textInputEditText, tiConfirmPassword, tiEmailSGnUp, tiPassword,
          tiUsernameSGnUp, tvWelcome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
