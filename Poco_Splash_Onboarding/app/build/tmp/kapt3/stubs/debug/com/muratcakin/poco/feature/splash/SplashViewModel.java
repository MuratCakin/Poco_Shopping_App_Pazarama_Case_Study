package com.muratcakin.poco.feature.splash;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/muratcakin/poco/feature/splash/SplashViewModel;", "Landroidx/lifecycle/ViewModel;", "dataStoreManager", "Lcom/muratcakin/poco/data/local/DataStoreManager;", "(Lcom/muratcakin/poco/data/local/DataStoreManager;)V", "_uiEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/muratcakin/poco/feature/splash/SplashViewEvent;", "uiEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getUiEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "checkOnBoardingVisibleStatus", "", "app_debug"})
public final class SplashViewModel extends androidx.lifecycle.ViewModel {
    private final com.muratcakin.poco.data.local.DataStoreManager dataStoreManager = null;
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.muratcakin.poco.feature.splash.SplashViewEvent> _uiEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.muratcakin.poco.feature.splash.SplashViewEvent> uiEvent = null;
    
    @javax.inject.Inject()
    public SplashViewModel(@org.jetbrains.annotations.NotNull()
    com.muratcakin.poco.data.local.DataStoreManager dataStoreManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.muratcakin.poco.feature.splash.SplashViewEvent> getUiEvent() {
        return null;
    }
    
    private final void checkOnBoardingVisibleStatus() {
    }
}