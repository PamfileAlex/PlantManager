package com.example.plantmanager.fragments;

import androidx.annotation.RequiresApi;
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
import androidx.lifecycle.ViewModelProvider;
import com.example.plantmanager.database.PlantDataAccess;

import com.example.plantmanager.databinding.FragmentHomeBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.NotificationsUtils;
import com.example.plantmanager.utils.OnItemListener;
import com.example.plantmanager.utils.PlantsRecyclerAdapter;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ApplicationViewModel applicationViewModel;

    private final OnItemListener<Plant> onItemListener = new OnItemListener<Plant>() {
        @Override
        public void onItemClick(Plant item, int position) {
            Toast.makeText(getActivity(), "clicked " + position, Toast.LENGTH_SHORT).show();
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

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

//        binding.btnAddPlant.setOnClickListener(view -> NavHostFragment.findNavController(HomeFragment.this)
//                .navigate(R.id.navigate_from_homeFragment_to_addPlantFragment));

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationViewModel = new ViewModelProvider(getActivity()).get(ApplicationViewModel.class);
        // TODO: Use the ViewModel
    }
}