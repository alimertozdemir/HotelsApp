package com.alimert.hotels.ui.splash;

import com.androidnetworking.error.ANError;
import com.alimert.hotels.data.DataManager;
import com.alimert.hotels.models.response.CityListResponse;
import com.alimert.hotels.ui.base.BasePresenterImpl;
import com.alimert.hotels.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SplashPresenterImpl<V extends SplashView> extends BasePresenterImpl<V>
        implements SplashPresenter<V> {

    @Inject
    public SplashPresenterImpl(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getCityListFromApi() {

        getView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .sendRequestToApi()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CityListResponse>() {
                    @Override
                    public void accept(CityListResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getView().hideLoading();
                        getView().setCityListToRecyclerView(response.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getView().hideLoading();

                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));


    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode()
                == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            //getView().openLoginActivity();
        } else {
            //getView().openMainActivity();
        }
    }
}
