package com.subhechhu.apidemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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

    // what is M-V-VM & why M-V-VM over M-V-C?
    // Structure of M-V-VM

    // Retrofit2
    // ViewModel Class
    // Repository Class


    // reference API Link: https://newsapi.org/docs/endpoints/
    // retrofit link: https://square.github.io/retrofit/

    RecyclerView recyclerView;
    ProgressBar progressBar_loader;

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview_news);
        progressBar_loader = findViewById(R.id.progressBar_loader);

        Log.e("TAGGED", "MainActivity.java onCreate()");

        // ViewModelProviders is a class that provides ViewModels for a scope(Activity).
        // In this case NewsViewModel class is the viewmodel as it extends ViewModel

        // ViewModel is responsible to provide data to the view,
        // so that view can put that data on the screen.
        // It also allows the user to interact with data and change the data.

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init(this);

        // Observe method will wait for the changes made in ViewModel class
        // It happens with the livedata used in VM class


        newsViewModel.getTopNewsRepository().observe(
                this,
                new Observer<MainResponse>() {
                    @Override
                    public void onChanged(MainResponse mainResponse) {
                        Log.e("TAGGED", "MainActivity.java onChanged()");
                        progressBar_loader.setVisibility(View.GONE);
                        List<NewsArticle> newsArticles = mainResponse.getArticles();
                        Log.e("TAGGED", "Articles: " + newsArticles.toString());
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


        findViewById(R.id.floatingActionButton_everything).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postNews();

                progressBar_loader.setVisibility(View.VISIBLE);
                articleArrayList.clear();
                newsAdapter.notifyDataSetChanged();

                newsViewModel.getEverything().observe(MainActivity.this,
                        new Observer<MainResponse>() {
                            @Override
                            public void onChanged(MainResponse mainResponse) {
                                progressBar_loader.setVisibility(View.GONE);
                                List<NewsArticle> newsArticles = mainResponse.getArticles();
                                Log.e("TAGGED", "Articles: " + newsArticles.toString());
                                articleArrayList.addAll(newsArticles);
                                newsAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });
    }

    void postNews() {
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setPublishedAt("today");
        newsArticle.setAuthor("Me");
        newsArticle.setContent("this is my content of news article");
        newsArticle.setDescription("this is description of my news");
        newsArticle.setUrlToImage("https://this_is_url_to_image");

        newsViewModel.postNews(newsArticle);
    }

    void deleteNews(){
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.getSource().getId();
        newsViewModel.deleteNews(Integer.parseInt(newsArticle.getSource().getId()));
    }
};