package com.alimert.hotels.network;

import com.alimert.hotels.models.response.CityListResponse;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<CityListResponse> sendRequestToApi();

}
