package com.example.gamers_chat.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamers_chat.R;

import java.util.ArrayList;

public class CustomAdapterUsers extends RecyclerView.Adapter<CustomAdapterUsers.MyViewHolder> {

    private ArrayList<UserProfile> dataSet;
    private ArrayList<UserProfile> originDataSet;
    private OnItemClickListener listener;

    public CustomAdapterUsers(ArrayList<UserProfile> dataSet, OnItemClickListener listener) {
        this.dataSet = new ArrayList<UserProfile>(dataSet);
        this.originDataSet = dataSet;
        this.listener = listener;

    }

    public void filter(String query) {
        dataSet.clear(); // Clear the current dataset
        if (query.isEmpty()) {
            dataSet.addAll(originDataSet); // Use addAll to avoid reference copy
        } else {
            query = query.toLowerCase().trim();
            for (UserProfile item : originDataSet) {
                if (item.getNickName().toLowerCase().trim().contains(query)) {
                    dataSet.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Notify adapter of dataset changes
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView;
        ImageView userProfilePicTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userCardNameTextView);
            userProfilePicTextView = itemView.findViewById(R.id.userCardProfileImageView);
        }

    }

    @NonNull
    @Override
    public CustomAdapterUsers.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_user, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterUsers.MyViewHolder holder, int position) {

        holder.userNameTextView.setText(dataSet.get(position).getNickName());
        holder.userProfilePicTextView.setImageResource(dataSet.get(position).getProfilePhoto());

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
        void onItemClick(UserProfile userProfile);
    }

}
