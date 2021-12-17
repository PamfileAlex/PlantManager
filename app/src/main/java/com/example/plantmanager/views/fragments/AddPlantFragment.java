package com.example.plantmanager.views.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.data_access.PlantDataAccess;
import com.example.plantmanager.databinding.FragmentAddPlantBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.LoggedUserManager;
import com.example.plantmanager.utils.ImageManager;
import com.example.plantmanager.utils.notification.NotificationsUtils;
import com.example.plantmanager.utils.PlantInfoCheck;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

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

        binding.btnAddImage.setOnClickListener(view -> imageManager.selectImage());

        binding.btnAdd.setOnClickListener(view -> {
            if (!areFieldsValid())
                return;
            Plant plant = getPlant();
            PlantDataAccess.insertPlant(plant, LoggedUserManager.INSTANCE.getLoggedUser().getId());
            applicationViewModel.addPlant(plant);

            NotificationsUtils.triggerNotification(getActivity(), plant);

            Toast.makeText(getActivity(), "Plant was added successfully!", Toast.LENGTH_SHORT).show();

            resetFields();
        });

        binding.dpDatepicker.setMinDate(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        binding.btnNotification.setOnClickListener(view -> {
            String uiPlantName = binding.etAddPlantName.getText().toString();
            String name = uiPlantName.isEmpty() ? "MyPlant" : uiPlantName;

            Plant plant = new Plant(100, 1, name, null, LocalDate.now(), LocalDate.now(), LocalTime.now().plusSeconds(5), binding.checkboxNotifications.isChecked());
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
        return PlantInfoCheck.plantNameCheck(getActivity(), binding.etAddPlantName) &&
                PlantInfoCheck.plantCategoryCheck(getActivity(), binding.categoryDropdown);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void resetFields() {
        binding.etAddPlantName.setText("");
        binding.categoryDropdown.setSelection(0);
        binding.image.setImageResource(R.drawable.plant);
        binding.checkboxNotifications.setChecked(true);

        LocalTime time = LocalTime.now();
        binding.tpTimepicker.setHour(time.getHour());
        binding.tpTimepicker.setMinute(time.getMinute());

        LocalDate date = LocalDate.now();
        binding.dpDatepicker.updateDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
    }
}