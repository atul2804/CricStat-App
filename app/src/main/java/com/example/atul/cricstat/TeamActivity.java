package com.example.atul.cricstat;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends Activity {

    private List<Team> itemlist;
    private TeamAdapter itemadapter;
    public Context context;
    ProgressBar progressBar;
    TextView textView;
    ListView listview;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemlist = new ArrayList<>();

        getitems();

    }

    public void getitems(){
        String url = "https://cricstat.000webhostapp.com/php/teams.php";
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

            if (s==null){
                Toast.makeText(TeamActivity.this,"Fail",Toast.LENGTH_SHORT).show();
            }else {
                try {
                    itemlist = new ArrayList<Team>();

                    JSONArray jarr = new JSONArray(s);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject obj = jarr.optJSONObject(i);
                        Team team = new Team();
                        team.setId(obj.optInt("ID"));
                        team.setName(obj.optString("Name"));
                        team.setBoard(obj.optString("Board"));
                        team.setLogo(obj.optString("Logo"));
                        team.setCaptain(obj.optString("Captain"));
                        team.setVicecaptain(obj.optString("ViceCaptain"));
                        team.setOdirank(obj.optString("ODIRank"));
                        team.setTestrank(obj.optString("TestRank"));
                        team.setTid(obj.optString("ID"));
                        itemlist.add(team);
                    }

                    adapter = new TeamAdapter(itemlist,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
