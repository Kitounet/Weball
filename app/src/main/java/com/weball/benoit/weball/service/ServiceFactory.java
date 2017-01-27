package com.weball.benoit.weball.service;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;

/**
 * Created by benoi on 08/12/2015.
 */

public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("OMG"))
                 .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
