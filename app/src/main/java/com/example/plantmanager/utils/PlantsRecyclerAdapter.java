package com.example.plantmanager.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmanager.databinding.PlantListItemBinding;
import com.example.plantmanager.models.Plant;

import java.util.List;

public class PlantsRecyclerAdapter extends RecyclerView.Adapter<PlantsRecyclerAdapter.ViewHolder> {
    private final List<Plant> plants;
    private final OnItemListener onItemListener;

    public PlantsRecyclerAdapter(List<Plant> plants, OnItemListener onItemListener) {
        this.plants = plants;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlantListItemBinding binding = PlantListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item = plants.get(position);
        holder.title.setText(holder.item.getTitle());
        //holder.view.setOnClickListener(v -> Toast.makeText(holder.view.getContext(), "clicked",Toast.LENGTH_LONG));
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Plant item;
        public final TextView title;
        public final View view;
        public final OnItemListener onItemListener;

        public ViewHolder(PlantListItemBinding binding, OnItemListener onItemListener) {
            super(binding.getRoot());
            this.title = binding.plantTitle;
            this.onItemListener = onItemListener;
            this.view = binding.getRoot();
            this.view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }

        @NonNull
        @Override
        public String toString() {
            return "";
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
