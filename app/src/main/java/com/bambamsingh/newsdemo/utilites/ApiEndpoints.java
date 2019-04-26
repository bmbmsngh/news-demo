package com.bambamsingh.newsdemo.utilites;

import com.bambamsingh.newsdemo.models.Response;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiEndpoints {
    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    Observable<Response> getMostPopularArticles(@QueryMap Map<String, String> options);
}