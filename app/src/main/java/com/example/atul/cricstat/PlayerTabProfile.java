package com.example.atul.cricstat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Atul on 26-09-2017.
 */

public class PlayerTabProfile extends Fragment {
    TextView name,team,age,role,battype,bowltype,birth;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playertabprofile,container,false);

        name = (TextView) view.findViewById(R.id.playername);
    //    team = (TextView) view.findViewById(R.id.countryname);
        age = (TextView) view.findViewById(R.id.age);
        role = (TextView) view.findViewById(R.id.role);
        battype = (TextView) view.findViewById(R.id.battype);
        bowltype = (TextView) view.findViewById(R.id.bowltype);
        birth = (TextView) view.findViewById(R.id.birthplace);

    //    Toast.makeText(getActivity(),getActivity().getIntent().getStringExtra("name"),Toast.LENGTH_SHORT).show();

        name.setText(getActivity().getIntent().getStringExtra("name"));
        age.setText(getActivity().getIntent().getStringExtra("age"));
        role.setText(getActivity().getIntent().getStringExtra("role"));
        battype.setText(getActivity().getIntent().getStringExtra("battype"));
        bowltype.setText(getActivity().getIntent().getStringExtra("bowltype"));
        birth.setText(getActivity().getIntent().getStringExtra("birth"));


        return view;
    }
}
