package com.example.atul.cricstat;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

/**
 * Created by Atul on 26-10-2017.
 */

public class TeamTabMatches extends Fragment {

    ProgressBar progressBar;
    TextView test;

    private List<Matches> itemlist;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    TextView emptytext;
    ImageView emptyimage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_matches,container,false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        emptytext = (TextView) view.findViewById(R.id.emptytext);
        emptyimage = (ImageView) view.findViewById(R.id.emptyimage);

        //   test = (TextView) findViewById(R.id.test_text);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemlist = new ArrayList<>();

        String id = getActivity().getIntent().getStringExtra("tid");

        getdata(id);

        return view;
    }

    public void getdata(String id){
        String url = "https://cricstat.000webhostapp.com/php/teammatches.php?id=" + id;
        new JsonMatches().execute(url);
    }

    public class JsonMatches extends AsyncTask<String,Void,String> {
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
               // Toast.makeText(MatchesActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                emptytext.setVisibility(View.VISIBLE);
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
                        matches.setOppologo(obj.optString("OppoLogo"));
                        matches.setHostlogo(obj.optString("HostLogo"));

                        itemlist.add(matches);
                    }

                    if (itemlist.size() == 0){
                        emptyimage.setVisibility(View.VISIBLE);
                        emptytext.setVisibility(View.VISIBLE);
                    }else{
                        emptytext.setVisibility(View.GONE);
                        emptyimage.setVisibility(View.GONE);
                    }

                    adapter = new MatchesAdapter(itemlist,getContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
