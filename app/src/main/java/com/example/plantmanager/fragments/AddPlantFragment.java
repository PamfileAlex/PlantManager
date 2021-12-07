package com.example.plantmanager.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.plantmanager.databinding.FragmentAddPlantBinding;
import com.example.plantmanager.utils.SpinnerHelper;
import com.example.plantmanager.view_models.ApplicationViewModel;

public class AddPlantFragment extends Fragment {

    FragmentAddPlantBinding binding;
    ApplicationViewModel applicationViewModel;

    private enum Options {
        CAMERA,
        GALLERY,
        NONE
    }

    private Options option;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationViewModel = new ViewModelProvider(getActivity()).get(ApplicationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPlantBinding.inflate(inflater, container, false);

        Spinner spinner = binding.customSpinner;
        SpinnerHelper.populateSpinnerWithCategories(spinner, getContext(), applicationViewModel.getCategories());

        binding.btnAddImage.setOnClickListener(view -> {
            selectImage();
        });
        return binding.getRoot();
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    switch (option){
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
                    option = Options.NONE;
                }
            });

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo"))
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                option = Options.CAMERA;
                activityResultLauncher.launch(intent);
            }
            else if (options[item].equals("Choose from Gallery"))
            {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                option = Options.GALLERY;
                activityResultLauncher.launch(intent);
            }
            else if (options[item].equals("Cancel")) {
                option = Options.NONE;
                dialog.dismiss();
            }
        });
        builder.show();
    }
}