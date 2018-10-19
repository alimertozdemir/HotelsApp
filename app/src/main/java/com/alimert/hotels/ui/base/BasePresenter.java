package com.alimert.hotels.ui.base;

import com.androidnetworking.error.ANError;

public interface BasePresenter<V extends BaseView> {

    void onAttach(V view);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();
}
