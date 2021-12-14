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

    private enum ImageOptions {
        CAMERA,
        GALLERY,
        NONE
    }

    private ImageOptions imageOption;

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

        Spinner categoryDropdown = binding.categoryDropdown;
        SpinnerHelper.populateSpinnerWithCategories(categoryDropdown, getContext(), applicationViewModel.getCategories());

        binding.btnAddImage.setOnClickListener(view -> {
            selectImage();
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

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    switch (imageOption) {
                        case CAMERA:
                            Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                            binding.image.setImageBitmap(bitmap);
                            break;
                        case GALLERY:
                            binding.image.setImageURI(intent.getData());
                            break;
                        case NONE:
                            break;
                    }
                    imageOption = ImageOptions.NONE;
                }
            });

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo")) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageOption = ImageOptions.CAMERA;
                activityResultLauncher.launch(intent);
            } else if (options[item].equals("Choose from Gallery")) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imageOption = ImageOptions.GALLERY;
                activityResultLauncher.launch(intent);
            } else if (options[item].equals("Cancel")) {
                imageOption = ImageOptions.NONE;
                dialog.dismiss();
            }
        });
        builder.show();
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