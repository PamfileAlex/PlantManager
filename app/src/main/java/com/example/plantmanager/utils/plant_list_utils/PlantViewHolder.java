package com.example.plantmanager.utils.plant_list_utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmanager.databinding.PlantListItemBinding;
import com.example.plantmanager.models.Plant;

public class PlantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Plant item;
    private final TextView title;
    private final TextView lastWatered;
    private final TextView nextWater;
    private final TextView time;
    private final ImageView image;
    private final OnItemListener<Plant> onItemListener;
    private final OnItemListener<Plant> onButtonPressed;

    public PlantViewHolder(PlantListItemBinding binding, OnItemListener<Plant> onItemListener, OnItemListener<Plant> onButtonPressed) {
        super(binding.getRoot());
        this.title = binding.tvPlantTitle;
        this.lastWatered = binding.tvLastWatered;
        this.nextWater = binding.tvNextWater;
        this.time = binding.tvHour;
        this.image = binding.plantImage;

        this.onItemListener = onItemListener;
        binding.getRoot().setOnClickListener(this);

        this.onButtonPressed = onButtonPressed;
        binding.btnWater.setOnClickListener(v -> {
            this.onButtonPressed.onItemClick(item, getAdapterPosition());
            onBind(item);
        });
    }

    public void onBind(Plant plant) {
        item = plant;
        title.setText(plant.getName());
        lastWatered.setText(plant.getLastWatered().toString());
        nextWater.setText(plant.getNextWater().toString());
        time.setText(plant.getTime().toString());
        if (plant.getImage() != null) {
            image.setImageBitmap(plant.getImage());
        }
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
