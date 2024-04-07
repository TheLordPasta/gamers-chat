package com.example.gamers_chat.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

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

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;
        TextView amountView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.productName);
            textViewVersion = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);
            amountView = itemView.findViewById(R.id.textAmount);
        }

    }

    @NonNull
    @Override
    public CustomAdapterGames.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterGames.MyViewHolder holder, int position) {

        holder.textViewName.setText(dataSet.get(position).getName());
        holder.textViewVersion.setText(dataSet.get(position).getPrice());
        holder.imageView.setImageResource(dataSet.get(position).getImage());

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
