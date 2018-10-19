package com.alimert.hotels.di.module;

import android.app.Application;
import android.content.Context;

import com.alimert.hotels.BuildConfig;
import com.alimert.hotels.data.AppDataManager;
import com.alimert.hotels.data.DataManager;
import com.alimert.hotels.data.prefs.AppPreferencesHelper;
import com.alimert.hotels.data.prefs.PreferencesHelper;
import com.alimert.hotels.di.ApiInfo;
import com.alimert.hotels.di.ApplicationContext;
import com.alimert.hotels.di.PreferenceInfo;
import com.alimert.hotels.network.ApiHeader;
import com.alimert.hotels.network.ApiHelper;
import com.alimert.hotels.network.ApiHelperImpl;
import com.alimert.hotels.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(ApiHelperImpl apiHelperImpl) {
        return apiHelperImpl;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }
}