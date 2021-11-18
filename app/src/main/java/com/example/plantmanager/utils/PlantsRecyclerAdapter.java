package com.example.plantmanager.utils;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmanager.databinding.PlantListItemBinding;
import com.example.plantmanager.models.Plant;

import java.util.List;

public class PlantsRecyclerAdapter extends RecyclerView.Adapter<PlantsRecyclerAdapter.ViewHolder> {
    private final List<Plant> plants;

    public PlantsRecyclerAdapter(List<Plant> plants) {
        this.plants = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PlantListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item = plants.get(position);
        holder.title.setText(holder.item.getTitle());
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public Plant item;
        public final TextView title;

        public ViewHolder(PlantListItemBinding binding) {
            super(binding.getRoot());
            title = binding.plantTitle;
        }

        @NonNull
        @Override
        public String toString() {
            return "";
        }
    }
}
