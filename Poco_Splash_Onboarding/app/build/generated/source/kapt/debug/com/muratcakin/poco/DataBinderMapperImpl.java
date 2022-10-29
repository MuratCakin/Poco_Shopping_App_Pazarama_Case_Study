package com.muratcakin.poco;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.muratcakin.poco.databinding.ActivityOnboardingBindingImpl;
import com.muratcakin.poco.databinding.ItemOnboardingBindingImpl;
import com.muratcakin.poco.databinding.ItemOnboardingThreeBindingImpl;
import com.muratcakin.poco.databinding.ItemOnboardingTwoBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYONBOARDING = 1;

  private static final int LAYOUT_ITEMONBOARDING = 2;

  private static final int LAYOUT_ITEMONBOARDINGTHREE = 3;

  private static final int LAYOUT_ITEMONBOARDINGTWO = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.muratcakin.poco.R.layout.activity_onboarding, LAYOUT_ACTIVITYONBOARDING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.muratcakin.poco.R.layout.item_onboarding, LAYOUT_ITEMONBOARDING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.muratcakin.poco.R.layout.item_onboarding_three, LAYOUT_ITEMONBOARDINGTHREE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.muratcakin.poco.R.layout.item_onboarding_two, LAYOUT_ITEMONBOARDINGTWO);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYONBOARDING: {
          if ("layout/activity_onboarding_0".equals(tag)) {
            return new ActivityOnboardingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_onboarding is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMONBOARDING: {
          if ("layout/item_onboarding_0".equals(tag)) {
            return new ItemOnboardingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_onboarding is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMONBOARDINGTHREE: {
          if ("layout/item_onboarding_three_0".equals(tag)) {
            return new ItemOnboardingThreeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_onboarding_three is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMONBOARDINGTWO: {
          if ("layout/item_onboarding_two_0".equals(tag)) {
            return new ItemOnboardingTwoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_onboarding_two is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "isLastPage");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/activity_onboarding_0", com.muratcakin.poco.R.layout.activity_onboarding);
      sKeys.put("layout/item_onboarding_0", com.muratcakin.poco.R.layout.item_onboarding);
      sKeys.put("layout/item_onboarding_three_0", com.muratcakin.poco.R.layout.item_onboarding_three);
      sKeys.put("layout/item_onboarding_two_0", com.muratcakin.poco.R.layout.item_onboarding_two);
    }
  }
}
