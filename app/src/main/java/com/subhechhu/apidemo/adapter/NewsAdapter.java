package com.subhechhu.apidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.subhechhu.apidemo.R;
import com.subhechhu.apidemo.model.NewsArticle;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<NewsArticle> articles;

    public NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        holder.textview_title.setText(articles.get(position).getTitle());
        Glide.with(context)
                .load(articles.get(position).getUrlToImage())
                .into(holder.imageview_header);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView textview_title;
        ImageView imageview_header;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_title = itemView.findViewById(R.id.textView_title);
            imageview_header = itemView.findViewById(R.id.imageView_header);
        }
    }
}