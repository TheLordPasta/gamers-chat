package com.example.gamers_chat.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.example.gamers_chat.R;
public class CustomAdapterGames extends RecyclerView.Adapter<CustomAdapterGames.MyViewHolder> {

    private ArrayList<GameModel> dataSet;
    private ArrayList<GameModel> originDataSet;
    private OnItemClickListener listener;

    public CustomAdapterGames(ArrayList<GameModel> dataSet, OnItemClickListener listener) {
        this.dataSet = new ArrayList<GameModel>(dataSet);
        this.originDataSet = dataSet;
        this.listener = listener;

    }

    public void filter(String query, String searchBy) {
        dataSet.clear(); // Clear the current dataset
        boolean isContains = false;
        if (query.isEmpty()) {
            dataSet.addAll(originDataSet); // Use addAll to avoid reference copy
        } else {
            query = query.toLowerCase().trim();
            for (GameModel item : originDataSet) {
                if(searchBy == "name") {
                    isContains = item.title.toLowerCase().trim().contains(query);
                }else if(searchBy == "genre") {
                    isContains = item.genre.toLowerCase().trim().contains(query);
                }else if (searchBy == "developer") {
                    isContains = item.developer.toLowerCase().trim().contains(query);
                }else if (searchBy == "year") {
                    isContains = item.release_date.toLowerCase().trim().contains(query);
                }else if (searchBy == "platform") {
                    isContains = item.platform.toLowerCase().trim().contains(query);
                }else if (searchBy == "publisher") {
                    isContains = item.publisher.toLowerCase().trim().contains(query);
                }
                if (isContains == true) {
                    dataSet.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Notify adapter of dataset changes
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView gameTitle;
        TextView gameReleaseDate;
        ImageView gameBannerThumbnail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameCardTitleText);
            gameReleaseDate = itemView.findViewById(R.id.gameCardDateText);
            gameBannerThumbnail = itemView.findViewById(R.id.gameCardThumbnail);

        }



    }

    @NonNull
    @Override
    public CustomAdapterGames.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_gamecard, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterGames.MyViewHolder holder, int i) {

        holder.gameTitle.setText(dataSet.get(i).title);
        holder.gameReleaseDate.setText(dataSet.get(i).release_date);

        Glide.with(holder.itemView.getContext())
                        .load(dataSet.get(i).thumbnail)
                        .into(holder.gameBannerThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(dataSet.get(i), v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface OnItemClickListener {
        void onItemClick(GameModel gameProfile, View v);
    }

}
