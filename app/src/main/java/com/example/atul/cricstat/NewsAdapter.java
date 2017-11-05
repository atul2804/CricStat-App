package com.example.atul.cricstat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Atul on 18-10-2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> searchResults;
    Context context;

    public NewsAdapter(List<News> searchResults,Context context) {

        //this.activity = activity;
        this.searchResults = searchResults;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final News news = searchResults.get(position);
        holder.newstitle.setText(news.getTitle());
        holder.article.setText(news.getArticle());
        Picasso.with(context).load(news.getImage()).into(holder.newslogo);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),NewsArticle.class);
                intent.putExtra("title",news.getTitle());
                intent.putExtra("image",news.getImage());
                intent.putExtra("date",news.getDate());
               intent.putExtra("article",news.getArticle());
                intent.putExtra("author",news.getAuthor());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView newstitle,article;
        public ImageView newslogo;
        public LinearLayout linearLayout;

        public ViewHolder(View view){
            super(view);

            newstitle = (TextView) view.findViewById(R.id.title);
            article = (TextView) view.findViewById(R.id.article);
            newslogo = (ImageView) view.findViewById(R.id.logo);

            linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

        }
    }
}
