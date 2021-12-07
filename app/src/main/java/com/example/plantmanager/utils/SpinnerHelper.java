package com.example.plantmanager.utils;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.plantmanager.R;
import com.example.plantmanager.models.Category;

import java.util.ArrayList;

public class SpinnerHelper {
    public static void populateSpinnerWithCategories(Spinner spinner, android.content.Context context, ArrayList<Category> categories) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(context, R.layout.dropdown_item, categories);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinner.setAdapter(adapter);
    }
}
