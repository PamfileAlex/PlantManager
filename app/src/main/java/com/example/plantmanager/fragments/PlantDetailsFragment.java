package com.example.plantmanager.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.plantmanager.databinding.FragmentPlantDetailsBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.ImageManager;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;
import com.example.plantmanager.view_models.PlantDetailsViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class PlantDetailsFragment extends DialogFragment {
    private FragmentPlantDetailsBinding binding;
    private PlantDetailsViewModel plantDetailsViewModel;
    private ApplicationViewModel applicationViewModel;

    private ImageManager imageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationViewModel = new ViewModelProvider(getActivity()).get(ApplicationViewModel.class);
        plantDetailsViewModel = new ViewModelProvider(getActivity()).get(PlantDetailsViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlantDetailsBinding.inflate(inflater, container, false);

        imageManager = new ImageManager(this, binding.image);

        populateFields();

        binding.btnChangeImage.setOnClickListener(view -> {
            imageManager.selectImage();
        });

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void populateFields() {
        Plant selectedPlant = plantDetailsViewModel.getSelectedPlant();
        if (selectedPlant == null)
            return;

        binding.etPlantName.setText(selectedPlant.getName());

        SpinnerHelper.populateSpinnerWithCategories(binding.categoryDropdown, getContext(), applicationViewModel.getCategories());
        binding.categoryDropdown.setSelection(getCategoryPosition(binding.categoryDropdown, selectedPlant.getIdCategory()));

        if (selectedPlant.getImage() != null)
            binding.image.setImageBitmap(selectedPlant.getImage());

        binding.checkboxNotifications.setChecked(selectedPlant.getAllowNotifications());

        binding.dpDatepicker.setMinDate(getMinDate(selectedPlant.getLastWatered()));

        LocalDate nextWater = selectedPlant.getNextWater();
        binding.dpDatepicker.updateDate(nextWater.getYear(), nextWater.getMonthValue() - 1, nextWater.getDayOfMonth());

        binding.tpTimepicker.setHour(selectedPlant.getTime().getHour());
        binding.tpTimepicker.setMinute(selectedPlant.getTime().getMinute());
    }

    private int getCategoryPosition(Spinner categoryDropdown, int categoryId) {
        for (int index = 0; index < categoryDropdown.getCount(); ++index) {
            if (((Category) categoryDropdown.getItemAtPosition(index)).getId() == categoryId) {
                return index;
            }
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private long getMinDate(LocalDate date) {
        if (date.isEqual(LocalDate.now())) {
            return LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}