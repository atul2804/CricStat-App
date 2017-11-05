package com.example.atul.cricstat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atul on 22-09-2017.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Team> searchResults;
    Context context;

    public TeamAdapter(List<Team> searchResults,Context context) {

        //this.activity = activity;
        this.searchResults = searchResults;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_view_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Team team = searchResults.get(position);
        holder.teamname.setText(team.getName());
        holder.boardname.setText(team.getBoard());
        holder.captain.setText(team.getCaptain());
        Picasso.with(context).load(team.getLogo()).into(holder.teamlogo);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Toast.makeText(context,"Selected : " + team.getId(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),TeamProfile.class);
                intent.putExtra("name",team.getName());
                intent.putExtra("captain",team.getCaptain());
                intent.putExtra("vice",team.getVicecaptain());
                intent.putExtra("board",team.getBoard());
                intent.putExtra("odi",team.getOdirank());
                intent.putExtra("test",team.getTestrank());
                intent.putExtra("logo",team.getLogo());
                intent.putExtra("tid",team.getTid());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView teamname,boardname,captain;
        public ImageView teamlogo;
        public LinearLayout linearLayout;

        public ViewHolder(View view){
            super(view);

            teamname = (TextView) view.findViewById(R.id.teamname);
            boardname = (TextView) view.findViewById(R.id.board);
            captain = (TextView) view.findViewById(R.id.captain);
            teamlogo = (ImageView) view.findViewById(R.id.logo);

            linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

        }
    }
}
