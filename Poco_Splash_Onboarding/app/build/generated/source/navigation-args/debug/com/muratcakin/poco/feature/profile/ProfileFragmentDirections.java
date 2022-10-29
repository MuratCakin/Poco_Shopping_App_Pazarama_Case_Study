package com.muratcakin.poco.feature.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.muratcakin.poco.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileFragmentToSignInFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_signInFragment);
  }
}
