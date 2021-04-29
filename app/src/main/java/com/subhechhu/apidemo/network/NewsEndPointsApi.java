package com.subhechhu.apidemo.network;


import com.subhechhu.apidemo.model.MainResponse;
import com.subhechhu.apidemo.model.NewsArticle;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NewsEndPointsApi {

    // @GET gets the endpoint which HTTP method to use on the base url
    @GET("top-headlines")
    Call<MainResponse> getTopHeadlines(@Query("sources") String newsSource,
                                       @Query("apiKey") String apiKey);
    // https://newsapi.org/v2/top-headlines?sources=newsSource&apiKey=apiKey


    @GET("everything")
    Call<MainResponse> getEverything(@Query("q") String myQuery,
                                     @Query("apiKey") String myApiKey);
    // https://newapi.org/v2/everything?q=myQuery&apiKey=myApiKey


    @POST("post-comment")
    Call<MainResponse> createNews(@Body NewsArticle newsArticle);

    @DELETE("delete-news")
    Call<MainResponse> deleteNews(int id);

    // https://newapi.org/v2/post-comment
}