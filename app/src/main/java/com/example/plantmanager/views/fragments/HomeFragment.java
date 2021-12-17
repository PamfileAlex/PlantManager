package com.example.plantmanager.views.fragments;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plantmanager.data_access.PlantDataAccess;

import com.example.plantmanager.databinding.FragmentHomeBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.notification.NotificationsUtils;
import com.example.plantmanager.utils.plant_list_utils.OnItemListener;
import com.example.plantmanager.utils.plant_list_utils.PlantsRecyclerAdapter;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;
import com.example.plantmanager.view_models.PlantDetailsViewModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ApplicationViewModel applicationViewModel;
    private PlantDetailsViewModel plantDetailsViewModel;

    private final OnItemListener<Plant> onItemListener = new OnItemListener<Plant>() {
        @Override
        public void onItemClick(Plant item, int position) {
            plantDetailsViewModel.setSelectedPlant(item);

            DialogFragment plantDetailsFragment = new PlantDetailsFragment();
            plantDetailsFragment.show(getActivity().getSupportFragmentManager(), "plantDetails");
        }
    };

    private final OnItemListener<Plant> onButtonListener = new OnItemListener<Plant>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemClick(Plant item, int position) {
            LocalDate lastWatered = item.getLastWatered();
            LocalDate nextWater = item.getNextWater();

            long interval = ChronoUnit.DAYS.between(lastWatered, nextWater);

            if (nextWater.isAfter(LocalDate.now())) {
                Toast.makeText(getActivity(), "It's not the time to water this plant!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nextWater.plusDays(interval).isAfter(LocalDate.now())) {
                item.setLastWatered(nextWater);
                item.setNextWater(nextWater.plusDays(interval));
            } else {
                long numberOfIntervals = ChronoUnit.DAYS.between(lastWatered, LocalDate.now()) / interval;
                item.setLastWatered(lastWatered.plusDays(interval * numberOfIntervals));
                item.setNextWater(nextWater.plusDays(interval * numberOfIntervals));
            }

            PlantDataAccess.updatePlantDates(item);

            NotificationsUtils.triggerNotification(getActivity(), item);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        PlantsRecyclerAdapter plantsRecyclerAdapter = new PlantsRecyclerAdapter(applicationViewModel.getPlants(), onItemListener, onButtonListener);
        applicationViewModel.setPlantsRecyclerAdapter(plantsRecyclerAdapter);
        binding.plantList.setAdapter(plantsRecyclerAdapter);

        Spinner categoryDropdown = binding.categoryDropdown;

        categoryDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applicationViewModel.setCurrentCategory((Category) categoryDropdown.getSelectedItem());
                System.out.println("SELECTED CATEGORY: " + applicationViewModel.getCurrentCategory());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerHelper.populateSpinnerWithCategories(categoryDropdown, getContext(), applicationViewModel.getCategories());

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationViewModel = new ViewModelProvider(getActivity()).get(ApplicationViewModel.class);
        plantDetailsViewModel = new ViewModelProvider(getActivity()).get(PlantDetailsViewModel.class);
    }
}