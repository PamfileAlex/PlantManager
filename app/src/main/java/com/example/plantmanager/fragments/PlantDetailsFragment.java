package com.example.plantmanager.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.databinding.FragmentPlantDetailsBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.ImageManager;
import com.example.plantmanager.utils.NotificationsUtils;
import com.example.plantmanager.utils.PlantInfoCheck;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;
import com.example.plantmanager.view_models.PlantDetailsViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        binding.btnUpdate.setOnClickListener(view -> {
            updatePlant();
        });

        binding.btnDelete.setOnClickListener(view -> {
            deletePlant();
            dismiss();
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

    private boolean areFieldsValid() {
        return PlantInfoCheck.plantNameCheck(getActivity(), binding.etPlantName) &&
                PlantInfoCheck.plantCategoryCheck(getActivity(), binding.categoryDropdown);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updatePlant() {
        if (!areFieldsValid())
            return;

        if (!checkTime())
            return;

        Plant selectedPlant = plantDetailsViewModel.getSelectedPlant();
        LocalDate previousNextWater = selectedPlant.getNextWater();
        LocalTime previousTime = selectedPlant.getTime();

        selectedPlant.setName(binding.etPlantName.getText().toString());
        selectedPlant.setImage(((BitmapDrawable) binding.image.getDrawable()).getBitmap());
        selectedPlant.setNextWater(LocalDate.of(binding.dpDatepicker.getYear(), binding.dpDatepicker.getMonth() + 1, binding.dpDatepicker.getDayOfMonth()));
        selectedPlant.setTime(LocalTime.of(binding.tpTimepicker.getHour(), binding.tpTimepicker.getMinute()));
        selectedPlant.setAllowNotifications(binding.checkboxNotifications.isChecked());

        PlantDataAccess.updatePlant(selectedPlant);
        applicationViewModel.notifyPlantsRecyclerAdapter();

        if (!previousNextWater.isEqual(selectedPlant.getNextWater()) ||
                previousTime.compareTo(selectedPlant.getTime()) != 0) {
            NotificationsUtils.triggerNotification(getActivity(), selectedPlant);
        }

        Toast.makeText(getActivity(), "Update succeeded!", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkTime() {
        LocalDate date = LocalDate.of(binding.dpDatepicker.getYear(), binding.dpDatepicker.getMonth() + 1, binding.dpDatepicker.getDayOfMonth());

        if (!date.isEqual(LocalDate.now()))
            return true;

        LocalTime time = LocalTime.of(binding.tpTimepicker.getHour(), binding.tpTimepicker.getMinute());
        if (time.isAfter(LocalTime.now()))
            return true;

        Toast.makeText(getActivity(), "Time before now!", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void deletePlant() {
        Plant plant = plantDetailsViewModel.getSelectedPlant();
        PlantDataAccess.deletePlant(plant);
        applicationViewModel.deletePlant(plant);
    }
}