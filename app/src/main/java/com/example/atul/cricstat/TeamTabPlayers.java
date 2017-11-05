package com.example.atul.cricstat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Atul on 23-09-2017.
 */

public class TeamTabPlayers extends android.app.Fragment {

    RecyclerView playersview;
    ProgressBar pbr1;

    public List<Players> itemlist;
    public RecyclerView.Adapter adapter;

    TextView test;
    String teamId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teamtabplayers, container, false);

        playersview = (RecyclerView) view.findViewById(R.id.recyclerplayers);
        pbr1 = (ProgressBar) view.findViewById(R.id.progress_bar1);

    //    test = (TextView) view.findViewById(R.id.text);

        playersview.setHasFixedSize(true);
        playersview.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemlist = new ArrayList<>();


     //   int id = getActivity().getIntent().getExtras().getInt("id");
    //    Toast.makeText(getActivity(),"Selected : " + id,Toast.LENGTH_SHORT).show();

        teamId = getActivity().getIntent().getExtras().getString("tid");

        getitems();
        return view;
    }

    public void getitems() {
        String url = "https://cricstat.000webhostapp.com/php/players.php?teamId=" + teamId;
        url = url.replaceAll(" ","%20");
        new TeamTabPlayers.JsonTeamtask().execute(url);
    }

    public class JsonTeamtask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            pbr1.setVisibility(View.VISIBLE);
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
            pbr1.setVisibility(View.GONE);
            if (s == null) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    itemlist = new ArrayList<Players>();

                    JSONArray jarr = new JSONArray(s);

                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject obj = jarr.optJSONObject(i);
                        Players player = new Players();
                        player.setId(obj.optInt("ID"));
                        player.setName(obj.optString("Name"));
                        player.setAge(obj.optString("Age"));
                        player.setBirth(obj.optString("BirthPlace"));
                        player.setBattype(obj.optString("BatStyle"));
                        player.setRole(obj.optString("Role"));
                        player.setBowltype(obj.optString("BowlStyle"));
                        player.setPlayerid(obj.optString("PlayerID"));

                        itemlist.add(player);
                    }

                    adapter = new PlayerAdapter(itemlist, getContext());
                    playersview.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
