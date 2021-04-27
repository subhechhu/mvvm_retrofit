package com.subhechhu.apidemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.subhechhu.apidemo.adapter.NewsAdapter;
import com.subhechhu.apidemo.model.NewsArticle;
import com.subhechhu.apidemo.model.MainResponse;
import com.subhechhu.apidemo.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // GET METHOD -> to get the data which is in the server
    // POST METHOD -> to add new data to the server

    // what is MVVM & why MVVM over MVC?
    // Structure of MVVM

    // ViewModel Class
    // Repository Class
    // Retrofit2

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvNews);

        // ViewModelProviders is a class that provides ViewModels for a scope.
        // In this case NewsViewModel class is the viewmodel as it extends ViewModel

        // ViewModel is responsible to provide data to the view,
        // so that view can put that data on the screen.
        // It also allows the user to interact with data and change the data.

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();

        // Observe method will wait for the changes made in ViewModel class
        // It happens with the livedata used in VM class
        newsViewModel.getNewsRepository().observe(this,
                new Observer<MainResponse>() {
                    @Override
                    public void onChanged(MainResponse mainResponse) {
                        List<NewsArticle> newsArticles = mainResponse.getArticles();
                        articleArrayList.addAll(newsArticles);
                        newsAdapter.notifyDataSetChanged();
                    }
                });

        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }
}