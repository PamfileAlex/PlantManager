package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final TextView textView = (TextView)view.findViewById(R.id.textView2);
        textView.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.navigate_from_registerFragment_to_loginFragment);
        });
        return view;
    }
}