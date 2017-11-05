package com.example.atul.cricstat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Atul on 17-10-2017.
 */

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder>{

    private List<Series> searchResults;
    Context context;

    public SeriesAdapter(List<Series> searchResults,Context context) {

        //this.activity = activity;
        this.searchResults = searchResults;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_view_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Series series = searchResults.get(position);

        holder.sname.setText(series.getName());
        holder.shost.setText(series.getHost());
        holder.opponent.setText(series.getOpponent());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(),MatchesActivity.class);
             //   it.putExtra("host",series.getHost());
             //   it.putExtra("oppo",series.getOpponent());

                it.putExtra("id",series.getSid());
                it.putExtra("name",series.getName());

                view.getContext().startActivity(it);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView sname,shost,opponent,start_date,end_date;
        public TextView num_odi,num_test;
        public LinearLayout linearLayout;

        public ViewHolder(View view){
            super(view);

            sname = (TextView) view.findViewById(R.id.seriesname);
            shost = (TextView) view.findViewById(R.id.serieshost);
            opponent = (TextView) view.findViewById(R.id.seriesoppo);

            linearLayout = (LinearLayout) view.findViewById(R.id.serieslayout);

        }
    }
}
