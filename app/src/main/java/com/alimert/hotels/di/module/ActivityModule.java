package com.alimert.hotels.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.alimert.hotels.di.ActivityContext;
import com.alimert.hotels.di.PerActivity;
import com.alimert.hotels.ui.splash.SplashPresenter;
import com.alimert.hotels.ui.splash.SplashPresenterImpl;
import com.alimert.hotels.ui.splash.SplashView;
import com.alimert.hotels.utils.rx.AppSchedulerProvider;
import com.alimert.hotels.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashPresenter<SplashView> provideSplashPresenter(
            SplashPresenterImpl<SplashView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
