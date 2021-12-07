package com.example.plantmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        return binding.getRoot();
    }
}