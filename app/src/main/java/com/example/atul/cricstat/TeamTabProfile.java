package com.example.atul.cricstat;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Atul on 23-09-2017.
 */

public class TeamTabProfile extends Fragment {

    ImageView imageView;
    TextView name,board,captain,vicecaptain,odirank,testrank;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.teamtabprofile,container,false);

        imageView = (ImageView) v.findViewById(R.id.teamphoto);
        name = (TextView) v.findViewById(R.id.teamname);
        board = (TextView) v.findViewById(R.id.board);
        captain = (TextView) v.findViewById(R.id.captain);
        vicecaptain = (TextView) v.findViewById(R.id.vicecaptain);
        odirank = (TextView) v.findViewById(R.id.odirank);
        testrank = (TextView) v.findViewById(R.id.testrank);

        int id = getActivity().getIntent().getExtras().getInt("id");
     //   Toast.makeText(getActivity(),"Selected : " + id,Toast.LENGTH_SHORT).show();

        name.setText(getActivity().getIntent().getStringExtra("name"));
        board.setText(getActivity().getIntent().getStringExtra("board"));
        captain.setText(getActivity().getIntent().getStringExtra("captain"));
        vicecaptain.setText(getActivity().getIntent().getStringExtra("vice"));
        odirank.setText(getActivity().getIntent().getStringExtra("odi"));
        testrank.setText(getActivity().getIntent().getStringExtra("test"));

        String str = getActivity().getIntent().getStringExtra("logo");
        Picasso.with(getActivity()).load(str).into(imageView);

        return v;
    }
}
