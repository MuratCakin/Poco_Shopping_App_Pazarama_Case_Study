package com.muratcakin.poco.feature.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.muratcakin.poco.R;

public class SignUpFragmentDirections {
  private SignUpFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSignUpFragmentToSignInFragment() {
    return new ActionOnlyNavDirections(R.id.action_signUpFragment_to_signInFragment);
  }
}
