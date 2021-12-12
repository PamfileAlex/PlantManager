package com.example.plantmanager.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmanager.databinding.PlantListItemBinding;
import com.example.plantmanager.models.Plant;

import java.util.List;

public class PlantsRecyclerAdapter extends RecyclerView.Adapter<PlantsRecyclerAdapter.ViewHolder> {
    private final List<Plant> plants;
    private final OnItemListener<Plant> onItemListener;
    private final OnItemListener<Plant> onButtonPressed;

    public PlantsRecyclerAdapter(List<Plant> plants, OnItemListener<Plant> onItemListener, OnItemListener<Plant> onButtonPressed) {
        this.plants = plants;
        this.onItemListener = onItemListener;
        this.onButtonPressed = onButtonPressed;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlantListItemBinding binding = PlantListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding, onItemListener, onButtonPressed);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(plants.get(position));
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Plant item;
        private final TextView title;
        private final TextView lastWatered;
        private final TextView nextWater;
        private final TextView time;
        private final ImageView image;
        private final OnItemListener<Plant> onItemListener;
        private final OnItemListener<Plant> onButtonPressed;

        public ViewHolder(PlantListItemBinding binding, OnItemListener<Plant> onItemListener, OnItemListener<Plant> onButtonPressed) {
            super(binding.getRoot());
            this.title = binding.tvPlantTitle;
            this.lastWatered = binding.tvLastWatered;
            this.nextWater = binding.tvNextWater;
            this.time = binding.tvHour;
            this.image = binding.plantImage;

            this.onItemListener = onItemListener;
            binding.getRoot().setOnClickListener(this);

            this.onButtonPressed = onButtonPressed;
            binding.btnWater.setOnClickListener(v -> onButtonPressed.onItemClick(item, getAdapterPosition()));
        }

        public void onBind(Plant plant) {
            item = plant;
            title.setText(plant.getName());
            lastWatered.setText(plant.getLastWatered().toString());
            nextWater.setText(plant.getNextWater().toString());
            time.setText(plant.getTime().toString());
            image.setImageBitmap(plant.getImage());
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
