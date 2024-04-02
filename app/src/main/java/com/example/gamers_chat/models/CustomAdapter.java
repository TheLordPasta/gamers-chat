//package com.example.gamers_chat.models;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.gamechattingapp.R;
//
//import java.util.ArrayList;
//
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
//
//    private ArrayList<DataModel> dataSet;
//    private ArrayList<DataModel> originDataSet;
//    private OnItemClickListener listener;
//
//    public CustomAdapter(ArrayList<DataModel> dataSet, OnItemClickListener listener) {
//        this.dataSet = new ArrayList<DataModel>(dataSet);
//        this.originDataSet = dataSet;
//        this.listener = listener;
//
//    }
//
//    public void filter(String query) {
//        dataSet.clear(); // Clear the current dataset
//        if (query.isEmpty()) {
//            dataSet.addAll(originDataSet); // Use addAll to avoid reference copy
//        } else {
//            query = query.toLowerCase().trim();
//            for (DataModel item : originDataSet) {
//                if (item.getName().toLowerCase().trim().contains(query)) {
//                    dataSet.add(item);
//                }
//            }
//        }
//        notifyDataSetChanged(); // Notify adapter of dataset changes
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textViewName;
//        TextView textViewVersion;
//        ImageView imageView;
//        TextView amountView;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewName = itemView.findViewById(R.id.productName);
//            textViewVersion = itemView.findViewById(R.id.price);
//            imageView = itemView.findViewById(R.id.imageView);
//            amountView = itemView.findViewById(R.id.textAmount);
//        }
//
//    }
//
//    @NonNull
//    @Override
//    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow, parent, false);
//        MyViewHolder myViewHolder = new MyViewHolder(view);
//
//        return myViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
//
//        holder.textViewName.setText(dataSet.get(position).getName());
//        holder.textViewVersion.setText(dataSet.get(position).getPrice());
//        holder.imageView.setImageResource(dataSet.get(position).getImage());
//        Integer amount = dataSet.get(position).getAmount();
//        if (amount != 0)
//            holder.amountView.setText(amount.toString());
//        else
//            holder.amountView.setText("");
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onItemClick(dataSet.get(position));
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataSet.size();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(DataModel dataModel);
//    }
//
//}
