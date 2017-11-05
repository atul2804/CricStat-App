package com.example.atul.cricstat;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
 * Created by Atul on 18-10-2017.
 */

public class SortFragment1 extends Fragment{

    public SortFragment1(){

    }

    ProgressBar progressBar;

    public RecyclerView.Adapter adapter;

    public List<String> hello;

    public String[] array1;
    public String text1 = "";

    TextView textView;
    ListView listdisplay;

    List<String> data;

    ArrayAdapter<String> adapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.most_layout1,container,false);

        //    TextView hello = (TextView) view.findViewById(R.id.test1);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        listdisplay = (ListView) view.findViewById(R.id.listdisplay);

        array1 = new String[10];

        data = new ArrayList<String>();

        getlist();

        return view;
    }

    public void getlist(){
        String url = "https://cricstat.000webhostapp.com/php/mostruns.php";
        new Jsonlisttask().execute(url);
    }

    public class Jsonlisttask extends AsyncTask<String,Void,String> {
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
            try {
                JSONArray jarr = new JSONArray(s);
                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject obj = jarr.optJSONObject(i);

                    array1[i] = obj.getString("Name");
                    data.add(obj.optString("Name"));
                }

                adapter1 = new ArrayAdapter<String>(getContext(),R.layout.listitem,R.id.listtext,data);
                listdisplay.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
