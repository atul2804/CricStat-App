package com.example.atul.cricstat;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Atul on 03-10-2017.
 */

public class OddStat extends Fragment {

    TextView match1,inning1,run1,avg1,sr1,half1,cent1,best1;
    TextView match0,inning0,run0,avg0,sr0,half0,cent0,best0;
    ProgressBar progressBar;
    LinearLayout totallayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.odi_layout,container,false);


        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        totallayout = (LinearLayout) view.findViewById(R.id.total_layout);

        match1 = (TextView) view.findViewById(R.id.test_matches);
        inning1 = (TextView) view.findViewById(R.id.test_innings);
        run1 = (TextView) view.findViewById(R.id.test_runs);
        avg1 = (TextView) view.findViewById(R.id.test_avg);
        sr1 = (TextView) view.findViewById(R.id.test_sr);
        half1 = (TextView) view.findViewById(R.id.test_half);
        cent1 = (TextView) view.findViewById(R.id.test_cent);
        best1 = (TextView) view.findViewById(R.id.test_best);

        match0 = (TextView) view.findViewById(R.id.odi_matches);
        inning0 = (TextView) view.findViewById(R.id.odi_innings);
        run0 = (TextView) view.findViewById(R.id.odi_runs);
        avg0 = (TextView) view.findViewById(R.id.odi_avg);
        sr0 = (TextView) view.findViewById(R.id.odi_sr);
        half0 = (TextView) view.findViewById(R.id.odi_half);
        cent0 = (TextView) view.findViewById(R.id.odi_cent);
        best0 = (TextView) view.findViewById(R.id.odi_best);

        String pid1 = getActivity().getIntent().getStringExtra("pid");

        getstats(pid1);
        return view;
    }

    public void getstats(String pid){

        String url = "https://cricstat.000webhostapp.com/php/Batstat.php?id=" + pid;
        new OddStat.Jsonbowltask().execute(url);

    }

    public class Jsonbowltask extends AsyncTask<String,Void,String> {

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
                            run0.setText(obj.optString("Runs"));
                            avg0.setText(obj.optString("Avg"));
                            sr0.setText(obj.optString("SR"));
                            half0.setText(obj.optString("Half"));
                            cent0.setText(obj.optString("Century"));
                            best0.setText(obj.optString("High"));
                        }else{
                            match1.setText(obj.optString("Matches"));
                            inning1.setText(obj.optString("Innings"));
                            run1.setText(obj.optString("Runs"));
                            avg1.setText(obj.optString("Avg"));
                            sr1.setText(obj.optString("SR"));
                            half1.setText(obj.optString("Half"));
                            cent1.setText(obj.optString("Century"));
                            best1.setText(obj.optString("High"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
