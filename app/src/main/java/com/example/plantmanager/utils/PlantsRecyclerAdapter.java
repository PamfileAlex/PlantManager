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
    private final OnItemListener<Plant> onItemListener;

    public PlantsRecyclerAdapter(List<Plant> plants, OnItemListener<Plant> onItemListener) {
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
        holder.title.setText(holder.item.getName());
        //holder.view.setOnClickListener(v -> Toast.makeText(holder.view.getContext(), "clicked",Toast.LENGTH_LONG));
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Plant item;
        public final TextView title;
        public final OnItemListener<Plant> onItemListener;

        public ViewHolder(PlantListItemBinding binding, OnItemListener<Plant> onItemListener) {
            super(binding.getRoot());
            this.title = binding.plantTitle;
            this.onItemListener = onItemListener;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(item, getAdapterPosition());
        }

        @NonNull
        @Override
        public String toString() {
            return "";
        }
    }
}
