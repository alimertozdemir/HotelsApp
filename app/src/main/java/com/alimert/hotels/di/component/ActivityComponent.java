package com.alimert.hotels.di.component;

import com.alimert.hotels.di.PerActivity;
import com.alimert.hotels.di.module.ActivityModule;
import com.alimert.hotels.ui.splash.SplashActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

}
