package com.muratcakin.poco.feature.onboarding;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/muratcakin/poco/feature/onboarding/OnBoardingViewModel;", "Landroidx/lifecycle/ViewModel;", "dataStoreManager", "Lcom/muratcakin/poco/data/local/DataStoreManager;", "(Lcom/muratcakin/poco/data/local/DataStoreManager;)V", "setOnBoardingStatus", "", "app_debug"})
public final class OnBoardingViewModel extends androidx.lifecycle.ViewModel {
    private final com.muratcakin.poco.data.local.DataStoreManager dataStoreManager = null;
    
    @javax.inject.Inject()
    public OnBoardingViewModel(@org.jetbrains.annotations.NotNull()
    com.muratcakin.poco.data.local.DataStoreManager dataStoreManager) {
        super();
    }
    
    public final void setOnBoardingStatus() {
    }
}