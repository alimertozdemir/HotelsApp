package com.alimert.hotels.ui.splash;

import com.alimert.hotels.models.response.CityListData;
import com.alimert.hotels.ui.base.BaseView;

import java.util.List;

public interface SplashView extends BaseView {

    void setCityListToRecyclerView(List<CityListData> cityList);

}
