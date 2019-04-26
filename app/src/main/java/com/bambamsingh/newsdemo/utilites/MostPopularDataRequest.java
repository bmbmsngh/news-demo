package com.bambamsingh.newsdemo.utilites;

import com.bambamsingh.newsdemo.BuildConfig;
import com.bambamsingh.newsdemo.models.Response;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MostPopularDataRequest {

    private static final String TAG = MostPopularDataRequest.class.getSimpleName();
    private ApiEndpoints apiEndpoints;

    public MostPopularDataRequest() {
        this.apiEndpoints = RestClient.createService(ApiEndpoints.class);
    }

    public Observable<Response> getMostPopularArticles() {

        Map<String, String> data = new HashMap<>();
        data.put("api-key", BuildConfig.API_KEY);

        return apiEndpoints.getMostPopularArticles(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}