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

    private ArrayList<GameProfile> dataSet;
    private ArrayList<GameProfile> originDataSet;
    private OnItemClickListener listener;

    public CustomAdapterGames(ArrayList<GameProfile> dataSet, OnItemClickListener listener) {
        this.dataSet = new ArrayList<GameProfile>(dataSet);
        this.originDataSet = dataSet;
        this.listener = listener;

    }

    public void filter(String query) {
        dataSet.clear(); // Clear the current dataset
        if (query.isEmpty()) {
            dataSet.addAll(originDataSet); // Use addAll to avoid reference copy
        } else {
            query = query.toLowerCase().trim();
            for (GameProfile item : originDataSet) {
                if (item.getGameName().toLowerCase().trim().contains(query)) {
                    dataSet.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Notify adapter of dataset changes
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView publisherTextView;
        ImageView bannerImageView;
        TextView platformTextView;

        ListItemBinding binding;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // nameTextView = itemView.findViewById(R.id.gameCardNameTextView);
            //publisherTextView = itemView.findViewById(R.id.gameCardPublisherTextView);
            //bannerImageView = itemView.findViewById(R.id.gameCardImageView);
            //platformTextView = itemView.findViewById(R.id.gameCardPlatformTextView);

            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(GameModel game) {
            binding.title.setText(game.title);
            Glide.with(this.itemView.getContext())
                    .load(game.thumbnail)
                    .into(binding.gameImage);
            binding.genre.setText(game.genre);
            binding.description.setText(game.short_description);
            binding.platform.setText(game.platform);
            binding.publisher.setText(game.publisher);
            binding.developer.setText(game.developer);
            binding.releaseDate.setText(game.release_date);
        }


    }

    @NonNull
    @Override
    public CustomAdapterGames.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_game, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterGames.MyViewHolder holder, int position) {

        holder.nameTextView.setText(dataSet.get(position).getGameName());
        holder.publisherTextView.setText(dataSet.get(position).getPublisher());
        holder.bannerImageView.setImageResource(dataSet.get(position).getBannerImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(dataSet.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface OnItemClickListener {
        void onItemClick(GameProfile gameProfile);
    }

}
