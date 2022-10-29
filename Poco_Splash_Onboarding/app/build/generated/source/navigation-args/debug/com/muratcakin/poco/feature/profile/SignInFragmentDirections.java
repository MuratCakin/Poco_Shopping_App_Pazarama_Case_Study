package com.muratcakin.poco.feature.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.muratcakin.poco.R;

public class SignInFragmentDirections {
  private SignInFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSignInFragmentToSignUpFragment() {
    return new ActionOnlyNavDirections(R.id.action_signInFragment_to_signUpFragment);
  }
}
