package com.example.plantmanager.utils.plant_list_utils;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmanager.databinding.PlantListItemBinding;
import com.example.plantmanager.models.Plant;

import java.util.List;

public class PlantsRecyclerAdapter extends RecyclerView.Adapter<PlantViewHolder> {
    private final List<Plant> plants;
    private final OnItemListener<Plant> onItemListener;
    private final OnItemListener<Plant> onButtonPressed;

    public PlantsRecyclerAdapter(List<Plant> plants, OnItemListener<Plant> onItemListener, OnItemListener<Plant> onButtonPressed) {
        this.plants = plants;
        this.onItemListener = onItemListener;
        this.onButtonPressed = onButtonPressed;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlantListItemBinding binding = PlantListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlantViewHolder(binding, onItemListener, onButtonPressed);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.onBind(plants.get(position));
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }
}
