package com.example.atul.cricstat;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsArticle extends Activity {

    ImageView newsimage;
    TextView article,author,title,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);

        newsimage = (ImageView) findViewById(R.id.newsimage);
        article = (TextView) findViewById(R.id.full_article);
        author = (TextView) findViewById(R.id.newsauthor);
        title = (TextView) findViewById(R.id.newstitle);
        date = (TextView) findViewById(R.id.newsdate);

     //   getActionBar().setTitle("News");
        getActionBar().hide();

        article.setText(getIntent().getStringExtra("article"));
        title.setText(getIntent().getStringExtra("title"));
        author.setText(getIntent().getStringExtra("author"));
        date.setText(getIntent().getStringExtra("date"));

        Picasso.with(this).load(getIntent().getStringExtra("image")).into(newsimage);

    }
}
