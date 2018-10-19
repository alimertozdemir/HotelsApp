package com.alimert.hotels.ui.splash;

import com.alimert.hotels.di.PerActivity;
import com.alimert.hotels.ui.base.BasePresenter;

@PerActivity
public interface SplashPresenter<V extends SplashView> extends BasePresenter<V> {

    void getCityListFromApi();
}