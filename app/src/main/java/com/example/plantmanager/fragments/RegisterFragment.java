package com.example.plantmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.FragmentRegisterBinding;
import com.example.plantmanager.models.User;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.btnRegister.setOnClickListener(view -> NavHostFragment.findNavController(RegisterFragment.this)
                .navigate(R.id.navigate_from_registerFragment_to_loginFragment));

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageRegister(view);
            }
        });
        return binding.getRoot();
    }

    private void manageRegister(View view) {
        String lastName = binding.etRegisterLastName.getText().toString();
        String firstName = binding.etRegisterFirstName.getText().toString();
        String email = binding.etRegisterEmail.getText().toString();
        String username = binding.etRegisterUsername.getText().toString();
        String password = binding.etRegisterPassword.getText().toString();
        String confirmPassword = binding.etRegisterConfirmPassword.getText().toString();

        if (!checkPassword(password, confirmPassword)) {
            Toast.makeText(getContext(), R.string.not_same_password, Toast.LENGTH_SHORT).show();
            return;
        }

        if (UserDataAccess.getUser(username) != null) {
            Toast.makeText(getContext(), R.string.username_already_exists, Toast.LENGTH_SHORT).show();
            return;
        }

        if(lastName.equals("") || firstName.equals("") || email.equals("") ||
        username.equals("") || password.equals("") || confirmPassword.equals("")){
            Toast.makeText(getContext(), R.string.empty_field, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(lastName, firstName, email, username, password);
        UserDataAccess.insertUser(user);

        Toast.makeText(getContext(), R.string.register_succeeded, Toast.LENGTH_SHORT).show();

        NavHostFragment.findNavController(RegisterFragment.this)
                .navigate(R.id.navigate_from_registerFragment_to_loginFragment);
    }

    private boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}