package com.example.plantmanager.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.databinding.FragmentAddPlantBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.CurrentUser;
import com.example.plantmanager.utils.ImageManager;
import com.example.plantmanager.utils.NotificationsUtils;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class AddPlantFragment extends Fragment {

    FragmentAddPlantBinding binding;
    ApplicationViewModel applicationViewModel;

    ImageManager imageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationViewModel = new ViewModelProvider(getActivity()).get(ApplicationViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPlantBinding.inflate(inflater, container, false);

        imageManager = new ImageManager(this, binding.image);

        Spinner categoryDropdown = binding.categoryDropdown;
        SpinnerHelper.populateSpinnerWithCategories(categoryDropdown, getContext(), applicationViewModel.getCategories());

        binding.btnAddImage.setOnClickListener(view -> {
            imageManager.selectImage();
        });

        binding.btnAdd.setOnClickListener(view -> {
            if (!areFieldsValid())
                return;
            Plant plant = getPlant();
            PlantDataAccess.insertPlant(plant, CurrentUser.INSTANCE.getUser().getId());
            applicationViewModel.addPlant(plant);

            NotificationsUtils.triggerNotification(getActivity(), plant);

            Toast.makeText(getActivity(), "Plant was added successfully!", Toast.LENGTH_SHORT).show();
        });

        binding.dpDatepicker.setMinDate(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        binding.btnNotification.setOnClickListener(view -> {
            Plant plant = new Plant(100, 1, "MyPlant", null, LocalDate.now(), LocalDate.now(), LocalTime.now().plusSeconds(5), binding.checkboxNotifications.isChecked());
            NotificationsUtils.triggerNotification(getActivity(), plant);
        });

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Plant getPlant() {
        return new Plant(((Category) binding.categoryDropdown.getSelectedItem()).getId(),
                binding.etAddPlantName.getText().toString(),
                ((BitmapDrawable) binding.image.getDrawable()).getBitmap(),
                LocalDate.now(),
                LocalDate.of(binding.dpDatepicker.getYear(), binding.dpDatepicker.getMonth() + 1, binding.dpDatepicker.getDayOfMonth()),
                LocalTime.of(binding.tpTimepicker.getHour(), binding.tpTimepicker.getMinute()),
                binding.checkboxNotifications.isChecked()
        );
    }

    private boolean areFieldsValid() {
        if (binding.etAddPlantName.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Name can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (((Category) binding.categoryDropdown.getSelectedItem()).getId() == 1) {
            Toast.makeText(getActivity(), "Please select a category!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}