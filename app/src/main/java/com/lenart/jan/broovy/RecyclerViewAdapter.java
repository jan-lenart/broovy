package com.lenart.jan.broovy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenart.jan.broovy.model.HopViewItem;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<HopViewItem> hopViewItems;
    private Context context;

    public RecyclerViewAdapter(ArrayList<HopViewItem> hopViewItems, Context context) {
        this.hopViewItems = hopViewItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String hopName = hopViewItems.get(position).getName();
        String hopWeight = hopViewItems.get(position).getWeight();
        Integer boilTime = hopViewItems.get(position).getBoilTime();

        viewHolder.hopVariety.setText(hopName);
        viewHolder.hopWeight.setText(hopWeight);
        viewHolder.hopBoilTime.setText(boilTime.toString());
    }

    @Override
    public int getItemCount() {
        return hopViewItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView hopVariety;
        TextView hopBoilTime;
        TextView hopWeight;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            hopVariety = itemView.findViewById(R.id.hop_variety);
            hopBoilTime = itemView.findViewById(R.id.hop_boil_time);
            hopWeight = itemView.findViewById(R.id.hop_weight);
        }
    }

}
