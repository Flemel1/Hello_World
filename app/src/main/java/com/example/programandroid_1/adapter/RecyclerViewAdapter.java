package com.example.programandroid_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programandroid_1.R;
import com.example.programandroid_1.data.RecordItem;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RecordItem> recordItems;

    public RecyclerViewAdapter(Context context, ArrayList<RecordItem> recordItems) {
        this.context = context;
        this.recordItems = recordItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordItem record = recordItems.get(position);
        holder.posterImage.setImageResource(record.getIdImage());
        holder.titleText.setText(record.getTitle());
        holder.descriptionText.setText(record.getDescription());
    }

    @Override
    public int getItemCount() {
        return recordItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        TextView titleText;
        TextView descriptionText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.poster);
            titleText = itemView.findViewById(R.id.tv_title);
            descriptionText = itemView.findViewById(R.id.tv_description);
        }
    }
}
