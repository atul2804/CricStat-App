package com.example.atul.cricstat;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

/**
 * Created by Atul on 08-10-2017.
 */

public class BowlStat extends Fragment {

    TextView match1,inning1,wickets1,avg1,eco1,best1;
    TextView match0,inning0,wickets0,avg0,eco0,best0;
    ProgressBar progressBar;

    LinearLayout totallayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.bowl_layout,container,false);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        totallayout = (LinearLayout) view.findViewById(R.id.total_layout);

        match1 = (TextView) view.findViewById(R.id.test_matches);
        inning1 = (TextView) view.findViewById(R.id.test_innings);
        wickets1 = (TextView) view.findViewById(R.id.test_wickets);
        avg1 = (TextView) view.findViewById(R.id.test_avg);
        eco1 = (TextView) view.findViewById(R.id.test_eco);
        best1 = (TextView) view.findViewById(R.id.test_best);

        match0 = (TextView) view.findViewById(R.id.odi_matches);
        inning0 = (TextView) view.findViewById(R.id.odi_innings);
        wickets0 = (TextView) view.findViewById(R.id.odi_wickets);
        avg0 = (TextView) view.findViewById(R.id.odi_avg);
        eco0 = (TextView) view.findViewById(R.id.odi_eco);
        best0 = (TextView) view.findViewById(R.id.odi_best);


        String pid1 = getActivity().getIntent().getStringExtra("pid");
    //    Toast.makeText(getActivity(),getActivity().getIntent().getIntExtra("pid",102),Toast.LENGTH_SHORT).show();
    //    Toast.makeText(getActivity(),pid1,Toast.LENGTH_SHORT).show();
        getstats(pid1);

        return view;
    }

    public void getstats(String pid){
        String url = "https://cricstat.000webhostapp.com/php/Bowlstat.php?id=" + pid;
        new Jsonbowltask().execute(url);

    }

    public class Jsonbowltask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            totallayout.setVisibility(View.INVISIBLE);
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
            totallayout.setVisibility(View.VISIBLE);

            if (s==null){
                Toast.makeText(getActivity(),"Fail",Toast.LENGTH_SHORT).show();
            }else {
                try {
                    JSONArray jarr = new JSONArray(s);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject obj = jarr.optJSONObject(i);
                        if (obj.optInt("Type")==0){
                            match0.setText(obj.optString("Matches"));
                            inning0.setText(obj.optString("Innings"));
                            wickets0.setText(obj.optString("Wickets"));
                            avg0.setText(obj.optString("Avg"));
                            eco0.setText(obj.optString("Eco"));
                            best0.setText(obj.optString("Best"));
                        }else{
                            match1.setText(obj.optString("Matches"));
                            inning1.setText(obj.optString("Innings"));
                            wickets1.setText(obj.optString("Wickets"));
                            avg1.setText(obj.optString("Avg"));
                            eco1.setText(obj.optString("Eco"));
                            best1.setText(obj.optString("Best"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
