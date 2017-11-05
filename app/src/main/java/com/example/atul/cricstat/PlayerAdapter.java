package com.example.atul.cricstat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Atul on 23-09-2017.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Players> searchResults;
    Context context;

    public PlayerAdapter(List<Players> searchResults,Context context) {
        this.searchResults = searchResults;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_row,parent,false);
        return new PlayerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Players players = searchResults.get(position);
        holder.playername.setText(players.getName());
        holder.playerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PlayerProfile.class);
                intent.putExtra("name",players.getName());
                intent.putExtra("role",players.getRole());
                intent.putExtra("age",players.getAge());
                intent.putExtra("battype",players.getBattype());
                intent.putExtra("bowltype",players.getBowltype());
                intent.putExtra("birth",players.getBirth());
                intent.putExtra("pid",players.getPlayerid());

                view.getContext().startActivity(intent);
            //    Toast.makeText(context,"Selected : " + players.getPlayerid(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView playername;
   //     public ImageView playerlogo;
        public LinearLayout playerlayout;

        public ViewHolder(View view){
            super(view);

            playername = (TextView) view.findViewById(R.id.player_name);
            playerlayout = (LinearLayout) view.findViewById(R.id.player_layout);

     //       playerlogo = (ImageView) view.findViewById(R.id.player_photo);

        }
    }
}
