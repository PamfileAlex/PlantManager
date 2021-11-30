package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.FragmentLoginBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.tvLoginRegister.setOnClickListener(view -> NavHostFragment.findNavController(LoginFragment.this)
                .navigate(R.id.navigate_from_loginFragment_to_registerFragment));

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageLogin(view);
            }
        });

        return binding.getRoot();
    }

    private void manageLogin(View view) {
        User user = UserDataAccess.getUser(binding.etLoginUsername.getText().toString(),
                binding.etLoginPassword.getText().toString());
        if (user == null) {
            Toast.makeText(getContext(), R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.navigate_from_loginFragment_to_homeFragment);
    }
}