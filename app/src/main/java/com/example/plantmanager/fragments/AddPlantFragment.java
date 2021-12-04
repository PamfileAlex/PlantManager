package com.example.plantmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.plantmanager.databinding.FragmentAddPlantBinding;
import com.example.plantmanager.utils.SpinnerHelper;

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