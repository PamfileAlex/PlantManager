package com.example.plantmanager.utils;

import android.app.Activity;
import android.app.AlertDialog;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.plantmanager.R;

public class ImageManager {
    private enum ImageOptions {
        CAMERA,
        GALLERY,
        NONE
    }

    private ImageOptions imageOption;

    private final Fragment fragment;
    private final ImageView imageView;

    private final ActivityResultLauncher<Intent> activityResultLauncher;

    public ImageManager(Fragment fragment, ImageView imageView) {
        this.imageOption = ImageOptions.NONE;

        this.fragment = fragment;
        this.imageView = imageView;

        this.activityResultLauncher = registerForActivityResult();
    }

    private ActivityResultLauncher<Intent> registerForActivityResult() {
        return fragment.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        switch (imageOption) {
                            case CAMERA:
                                Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                                imageView.setImageBitmap(bitmap);
                                break;
                            case GALLERY:
                                imageView.setImageURI(intent.getData());
                                break;
                            case NONE:
                                break;
                        }
                        imageOption = ImageOptions.NONE;
                    }
                });
    }

    public void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        builder.setTitle(R.string.add_photo);
        builder.setItems(options, (dialog, position) -> {
            Intent intent;
            switch (position){
                case 0:
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    imageOption = ImageOptions.CAMERA;
                    activityResultLauncher.launch(intent);
                    break;

                case 1:
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    imageOption = ImageOptions.GALLERY;
                    activityResultLauncher.launch(intent);
                    break;

                case 2:
                    imageOption = ImageOptions.NONE;
                    dialog.dismiss();
                    break;

                default:
                    break;
            }
        });
        builder.show();
    }
}
