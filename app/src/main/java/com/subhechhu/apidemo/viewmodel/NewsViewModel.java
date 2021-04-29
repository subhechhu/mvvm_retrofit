package com.subhechhu.apidemo.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.subhechhu.apidemo.R;
import com.subhechhu.apidemo.model.MainResponse;
import com.subhechhu.apidemo.model.NewsArticle;
import com.subhechhu.apidemo.repo.NewsRepository;


public class NewsViewModel extends ViewModel {

    Context context;
    NewsRepository newsRepository;

    // LiveData is a data holder that contains primitive/collection types.
    // It's used for observing changes in the view and updating the view when it is ACTIVE


    // MutableLiveData is just a class that extends the LiveData type class.
    // MutableLiveData is commonly used since it provides the postValue(), setValue()
    // methods which  LiveData class doesn't provide.

    private MutableLiveData<MainResponse> mutableLiveData_topnews;
    private MutableLiveData<MainResponse> mutableLiveData_everything;

    public void init(Context context) {
        Log.e("TAGGED", "NewsViewModel.java init()");
        this.context = context;
        if (mutableLiveData_topnews != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance(); // get instance of Repository class
        mutableLiveData_topnews = newsRepository.getHeadlineNews("google-news",
                context.getResources().getString(R.string.news_api_key)); // get the data from the api end point and store it as mutablelivedata

        mutableLiveData_everything = newsRepository.getEverything("tesla",
                context.getResources().getString(R.string.news_api_key));
    }

    public LiveData<MainResponse> getTopNewsRepository() {
        Log.e("TAGGED", "NewsViewModel.java getNewsRepository()");
        return mutableLiveData_topnews; // return the mutablelivedata that we get in line #30
    }

    public LiveData<MainResponse> getEverything() {
        return mutableLiveData_everything;
    }

    public void postNews(NewsArticle newsArticle) {
        newsRepository.postNews(newsArticle);
    }

    public void deleteNews(int id) {
        newsRepository.deleteNews(id);
    }
}