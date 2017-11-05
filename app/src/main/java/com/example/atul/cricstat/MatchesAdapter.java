package com.example.atul.cricstat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Atul on 18-10-2017.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder>{

    private List<Matches> searchResults;
    Context context;

    public MatchesAdapter(List<Matches> searchResults,Context context) {
        this.searchResults = searchResults;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_view_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Matches matches = searchResults.get(position);

        holder.mtime.setText(matches.getTime());
        holder.mhost.setText(matches.getHost());
        holder.venue.setText(matches.getVenue());
        holder.opponent.setText(matches.getOppo());
        holder.mdate.setText(matches.getDate());

        Picasso.with(context).load(matches.getHostlogo()).into(holder.hostlogo);
        Picasso.with(context).load(matches.getOppologo()).into(holder.oppologo);

        if (matches.getMtype().equals("1")){
            holder.mtype.setText("Test Match");
        }else{
            holder.mtype.setText("ODI Match");
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mhost,opponent,mtime,venue,mdate,mtype;
        public ImageView hostlogo,oppologo;

        public ViewHolder(View view){
            super(view);

            mhost = (TextView) view.findViewById(R.id.host);
            opponent = (TextView) view.findViewById(R.id.oppo);
            mtime = (TextView) view.findViewById(R.id.mtime);
            venue = (TextView) view.findViewById(R.id.matchvenue);
            mdate = (TextView) view.findViewById(R.id.matchdate);
            mtype = (TextView) view.findViewById(R.id.matchtype);
            hostlogo = (ImageView) view.findViewById(R.id.hostlogo);
            oppologo = (ImageView) view.findViewById(R.id.oppologo);

        }
    }
}
