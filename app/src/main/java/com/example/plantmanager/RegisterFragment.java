package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantmanager.database.UserManager;
import com.example.plantmanager.databinding.FragmentRegisterBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.SqlConnectionManager;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Snackbar snackbar;

        if (!checkPassword(binding.etRegisterPassword.getText().toString(),
                binding.etRegisterConfirmPassword.getText().toString())) {
            snackbar = Snackbar.make(view, R.string.not_same_password, 4000);
            snackbar.show();
            return;
        }

        if (!checkUsername(binding.etRegisterUsername.getText().toString())) {
            snackbar = Snackbar.make(view, R.string.username_already_exists, 4000);
            snackbar.show();
            return;
        }

        User user = new User(binding.etRegisterLastName.getText().toString(),
                binding.etRegisterFirstName.getText().toString(),
                binding.etRegisterEmail.getText().toString(),
                binding.etRegisterUsername.getText().toString(),
                binding.etRegisterPassword.getText().toString());
        addUser(user);

        snackbar = Snackbar.make(view, R.string.register_succeeded, 4000);
        snackbar.show();

        NavHostFragment.findNavController(RegisterFragment.this)
                .navigate(R.id.navigate_from_registerFragment_to_loginFragment);
    }

    private boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean checkUsername(String username) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        UserManager userManger = new UserManager();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    this.getString(R.string.db_classes),
                    this.getString(R.string.db_connection_url));
            ArrayList<User> users = userManger.getUsers(databaseConnection);

            for (User user : users) {
                if (user.getUsername().equals(username))
                    return false;
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void addUser(User user) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        UserManager userManger = new UserManager();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    this.getString(R.string.db_classes),
                    this.getString(R.string.db_connection_url));
            userManger.insertUser(databaseConnection, user);
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}