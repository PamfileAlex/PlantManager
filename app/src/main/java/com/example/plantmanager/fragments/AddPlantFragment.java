package com.example.plantmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.plantmanager.R;
import com.example.plantmanager.database.CategoryDataAccess;
import com.example.plantmanager.databinding.FragmentAddPlantBinding;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.utils.SpinnerHelper;

import java.util.ArrayList;

public class AddPlantFragment extends Fragment {

    FragmentAddPlantBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPlantBinding.inflate(inflater, container, false);

        Spinner spinner = binding.customSpinner;
        SpinnerHelper.populateSpinnerWithCategories(spinner, getContext());

        return binding.getRoot();
    }
}