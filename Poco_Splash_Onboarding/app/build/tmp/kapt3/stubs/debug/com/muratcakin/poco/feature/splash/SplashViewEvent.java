package com.muratcakin.poco.feature.splash;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/muratcakin/poco/feature/splash/SplashViewEvent;", "", "()V", "NavigateToMain", "NavigateToOnBoarding", "Lcom/muratcakin/poco/feature/splash/SplashViewEvent$NavigateToMain;", "Lcom/muratcakin/poco/feature/splash/SplashViewEvent$NavigateToOnBoarding;", "app_debug"})
public abstract class SplashViewEvent {
    
    private SplashViewEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/muratcakin/poco/feature/splash/SplashViewEvent$NavigateToOnBoarding;", "Lcom/muratcakin/poco/feature/splash/SplashViewEvent;", "()V", "app_debug"})
    public static final class NavigateToOnBoarding extends com.muratcakin.poco.feature.splash.SplashViewEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.muratcakin.poco.feature.splash.SplashViewEvent.NavigateToOnBoarding INSTANCE = null;
        
        private NavigateToOnBoarding() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/muratcakin/poco/feature/splash/SplashViewEvent$NavigateToMain;", "Lcom/muratcakin/poco/feature/splash/SplashViewEvent;", "()V", "app_debug"})
    public static final class NavigateToMain extends com.muratcakin.poco.feature.splash.SplashViewEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.muratcakin.poco.feature.splash.SplashViewEvent.NavigateToMain INSTANCE = null;
        
        private NavigateToMain() {
            super();
        }
    }
}