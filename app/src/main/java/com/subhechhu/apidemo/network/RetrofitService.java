package com.subhechhu.apidemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    // To construct an HTTP request call, we need to build our Retrofit object
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/") // base URL which is going to be used for every api call
            // Converter factory below takes care of the parsing of data we send to server (POST API) & on the responses we get
            // We have used GsonConverterFactory to convert JSON data to the NewsResponse class
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}