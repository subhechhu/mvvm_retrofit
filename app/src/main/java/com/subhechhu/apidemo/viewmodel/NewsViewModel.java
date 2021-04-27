package com.subhechhu.apidemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.subhechhu.apidemo.model.MainResponse;
import com.subhechhu.apidemo.repo.NewsRepository;


public class NewsViewModel extends ViewModel {

    // LiveData is a data holder that contains primitive/collection types.
    // It's used for observing changes in the view and updating the view when it is ACTIVE


    // MutableLiveData is just a class that extends the LiveData type class.
    // MutableLiveData is commonly used since it provides the postValue(), setValue()
    // methods which  LiveData class doesn't provide.

    private MutableLiveData<MainResponse> mutableLiveData;

    String api_key = "d6f719adc7a447b792231cf1c985b463";

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        NewsRepository newsRepository = NewsRepository.getInstance(); // get instance of Repository class
        mutableLiveData = newsRepository.getHeadlineNews("google-news", api_key); // get the data from the api end point and store it as mutablelivedata
    }

    public LiveData<MainResponse> getNewsRepository() {
        return mutableLiveData; // return the mutablelivedata that we get in line #30
    }
}