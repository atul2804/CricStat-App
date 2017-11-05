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

public class SeriesActivity extends Activity {

    ProgressBar progressBar;
    private List<Series> itemlist;
    TextView test;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    //    test = (TextView) findViewById(R.id.test_text);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemlist = new ArrayList<>();

        getdata();

    }

    public void getdata(){
        String url = "https://cricstat.000webhostapp.com/php/series.php";
        new JsonSeriestask().execute(url);
    }

    public class JsonSeriestask extends AsyncTask<String,Void,String>{

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
                Toast.makeText(SeriesActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }else {
                try {
                    itemlist = new ArrayList<Series>();

                    JSONArray jarr = new JSONArray(s);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject obj = jarr.optJSONObject(i);
                        Series series = new Series();

                        series.setName(obj.optString("Name"));
                        series.setId(obj.optInt("ID"));
                        series.setHost(obj.optString("Host"));
                        series.setOpponent(obj.optString("Opponent"));
                        series.setNum_odi(obj.optInt("num_odi"));
                        series.setNum_test(obj.optInt("num_test"));
                        series.setStart_date(obj.optString("StartDate"));
                        series.setEnd_date(obj.optString("EndDate"));
                        series.setSid(obj.optString("ID"));
                        itemlist.add(series);
                    }

                    adapter = new SeriesAdapter(itemlist,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
