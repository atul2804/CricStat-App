package com.example.atul.cricstat;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class MatchesActivity extends Activity {

    ProgressBar progressBar;
    TextView test;

    private List<Matches> itemlist;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

     //   test = (TextView) findViewById(R.id.test_text);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemlist = new ArrayList<>();

        String id = getIntent().getExtras().getString("id");
    //    Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
        getActionBar().setTitle(getIntent().getStringExtra("name"));

        getdata(id);

    }

    public void getdata(String id){
        String url = "https://cricstat.000webhostapp.com/php/test.php?id=" + id;
        new JsonMatchestask().execute(url);
    }

    public class JsonMatchestask extends AsyncTask<String,Void,String>{
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
         //   test.setText(s);
            if (s==null){
                Toast.makeText(MatchesActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }else {
                try {
                    itemlist = new ArrayList<Matches>();

                    JSONArray jarr = new JSONArray(s);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject obj = jarr.optJSONObject(i);

                        Matches matches = new Matches();
                  //      matches.setId(obj.optInt("ID"));
                  //      matches.setSeriesid(obj.optInt("SeriesID"));
                        matches.setVenue(obj.optString("Venue"));
                        matches.setDate(obj.optString("Date"));
                        matches.setTime(obj.optString("Time"));
                        matches.setHost(obj.optString("Host"));
                        matches.setOppo(obj.optString("Opponent"));
                        matches.setMtype(obj.optString("Type"));
                        matches.setHostlogo(obj.optString("HostLogo"));
                        matches.setOppologo(obj.optString("OppoLogo"));

                        itemlist.add(matches);
                    }

                    adapter = new MatchesAdapter(itemlist,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
