package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantmanager.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRegisterBinding binding = FragmentRegisterBinding.inflate(inflater, container,false);

        binding.textView2.setOnClickListener(view -> NavHostFragment.findNavController(RegisterFragment.this)
                .navigate(R.id.navigate_from_registerFragment_to_loginFragment));

        return binding.getRoot();
    }
}