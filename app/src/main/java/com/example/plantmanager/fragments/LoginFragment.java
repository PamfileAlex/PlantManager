package com.example.plantmanager.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.FragmentLoginBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.CurrentUser;
import com.example.plantmanager.view_models.UserViewModel;

public class LoginFragment extends Fragment {
    private UserViewModel userViewModel;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    private void manageLogin(View view) {
        User user = UserDataAccess.getUser(binding.etLoginUsername.getText().toString(),
                binding.etLoginPassword.getText().toString());
        if (user == null) {
            Toast.makeText(getContext(), R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        userViewModel.setUser(user);
        CurrentUser.INSTANCE.setUser(user);
        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.navigate_from_loginFragment_to_mainFragment);
    }
}