package com.subhechhu.apidemo.repo;

import androidx.lifecycle.MutableLiveData;

import com.subhechhu.apidemo.model.MainResponse;
import com.subhechhu.apidemo.network.NewsEndPointsApi;
import com.subhechhu.apidemo.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    // When ViewModel requests the data that will be used for the View,
    // Repository will provide you this by choosing the appropriate data
    // locally or on the network

    private final NewsEndPointsApi newsEndPointsApi;
    private static NewsRepository newsRepository;

    public NewsRepository() {
        // createService takes Interface as it's parameter
        // Interface passed will have the end point which will be responsible for getting specific data
        newsEndPointsApi = RetrofitService
                .createService(NewsEndPointsApi.class); // Create Single instance of retrofit service object to get the data from the api
    }

    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            // creating instance of the current class
            // method getInstance() will create just one instance of the class
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    public MutableLiveData<MainResponse> getHeadlineNews(String sources, String key) {
        MutableLiveData<MainResponse> newsData = new MutableLiveData<>(); // to send the data to observable in the MainActivity
        newsEndPointsApi.getTopHeadlines(sources, key)
                .enqueue(new Callback<MainResponse>() { // Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response.
                    @Override
                    public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                        if (response.isSuccessful()) {
                            newsData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MainResponse> call, Throwable t) {
                        newsData.postValue(null);
                    }
                });
        return newsData;
    }
}