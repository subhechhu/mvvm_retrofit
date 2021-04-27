package com.subhechhu.apidemo.network;


import com.subhechhu.apidemo.model.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsEndPointsApi {

    // @GET gets the endpoint which HTTP method to use on the base url
    @GET("top-headlines")
    Call<MainResponse> getTopHeadlines(@Query("sources") String newsSource,
                                       @Query("apiKey") String apiKey);



    /*
    *
    *   @POST("set-news")
    *   Call<DataModal> createNews(@Body NewsArticle newsArticle);
    *
    * */
}