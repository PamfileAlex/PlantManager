package com.example.plantmanager.utils;

import android.app.Activity;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantmanager.models.Category;

public final class PlantInfoCheck {
    public static boolean plantNameCheck(Activity activity, TextView plantName){
        if (plantName.getText().toString().isEmpty()) {
            Toast.makeText(activity, "Name can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean plantCategoryCheck(Activity activity, Spinner categoryDropdown){
        if (((Category) categoryDropdown.getSelectedItem()).getId() == 1) {
            Toast.makeText(activity, "Please select a category!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
