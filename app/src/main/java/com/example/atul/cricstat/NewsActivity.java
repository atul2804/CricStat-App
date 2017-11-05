package com.example.atul.cricstat;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity {

    private List<News> itemlist;
    private NewsAdapter itemadapter;
    public Context context;
    ProgressBar progressBar;
    TextView textView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    //    textView = (TextView) findViewById(R.id.test1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemlist = new ArrayList<>();

        getitems();
    }

    public void getitems(){
        String url = "https://cricstat.000webhostapp.com/php/news.php";
        new JsonTeamtask().execute(url);
    }

    public class JsonTeamtask extends AsyncTask<String, Void, String>
    {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... param) {
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(param[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
        //    textView.setText(s);

            if (s==null){
                Toast.makeText(NewsActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }else {
                try {
                    itemlist = new ArrayList<News>();

                    JSONArray jarr = new JSONArray(s);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject obj = jarr.optJSONObject(i);

                        News news = new News();
                        news.setTitle(obj.optString("Title"));
                        news.setId(obj.optInt("ID"));
                        news.setArticle(obj.optString("Article"));
                        news.setAuthor(obj.optString("Author"));
                        news.setDate(obj.optString("Date"));
                        news.setImage(obj.optString("Image"));

                        itemlist.add(news);
                    }

                    adapter = new NewsAdapter(itemlist,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
