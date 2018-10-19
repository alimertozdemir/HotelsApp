package com.alimert.hotels.network;

import com.alimert.hotels.models.response.CityListResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class ApiHelperImpl implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public ApiHelperImpl(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<CityListResponse> sendRequestToApi() {
        return Rx2AndroidNetworking.get(ApiEndpoint.ENDPOINT_SERVER + "/{apiVersion}" + "/{uri}")
                .addPathParameter("apiVersion", "v1")
                .addPathParameter("uri", "city")
                //.addHeaders(mApiHeader.getPublicApiHeader())
                .build()
                .getObjectSingle(CityListResponse.class);
    }
}
