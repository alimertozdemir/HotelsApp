package com.alimert.hotels.di.component;

import android.app.Application;
import android.content.Context;

import com.alimert.hotels.HotelsApplication;
import com.alimert.hotels.data.DataManager;
import com.alimert.hotels.di.ApplicationContext;
import com.alimert.hotels.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(HotelsApplication app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}