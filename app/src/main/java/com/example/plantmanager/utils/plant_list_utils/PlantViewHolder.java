package com.example.plantmanager.utils.plant_list_utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmanager.R;
import com.example.plantmanager.databinding.PlantListItemBinding;
import com.example.plantmanager.models.Plant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class PlantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Plant item;
    private final TextView title;
    private final TextView lastWatered;
    private final TextView nextWater;
    private final TextView time;
    private final ImageView image;
    private final RelativeLayout statusColor;
    private final OnItemListener<Plant> onItemListener;
    private final OnItemListener<Plant> onButtonPressed;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PlantViewHolder(PlantListItemBinding binding, OnItemListener<Plant> onItemListener, OnItemListener<Plant> onButtonPressed) {
        super(binding.getRoot());
        this.title = binding.tvPlantTitle;
        this.lastWatered = binding.tvLastWatered;
        this.nextWater = binding.tvNextWater;
        this.time = binding.tvHour;
        this.image = binding.plantImage;
        this.statusColor = binding.colorLayout;

        this.onItemListener = onItemListener;
        binding.getRoot().setOnClickListener(this);

        this.onButtonPressed = onButtonPressed;
        binding.btnWater.setOnClickListener(v -> {
            this.onButtonPressed.onItemClick(item, getAdapterPosition());
            onBind(item);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    public void onBind(Plant plant) {
        item = plant;
        title.setText(plant.getName());
        lastWatered.setText(plant.getLastWatered().toString());
        nextWater.setText(plant.getNextWater().toString());
        time.setText(plant.getTime().toString());
        if (plant.getImage() != null) {
            image.setImageBitmap(plant.getImage());
        }

        statusColor.setBackgroundColor(getColor());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int getColor() {
        if (item.getNextWater().isAfter(LocalDate.now()) ||
                item.getNextWater().isEqual(LocalDate.now()) &&
                        item.getTime().isAfter(LocalTime.now()))
            return Color.BLUE;

        LocalDate previousDate = item.getNextWater();
        LocalDate nextDate = previousDate.plusDays(1);
        long interval = ChronoUnit.DAYS.between(item.getLastWatered(), item.getNextWater());

        if (LocalDate.now().isEqual(previousDate) || LocalDate.now().isEqual(nextDate))
            return Color.GREEN;

        previousDate = nextDate;
        nextDate = previousDate.plusDays(interval);

        if (LocalDate.now().compareTo(previousDate) >= 0 && LocalDate.now().compareTo(nextDate) <= 0)
            return Color.YELLOW;
        
        return Color.RED;
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
