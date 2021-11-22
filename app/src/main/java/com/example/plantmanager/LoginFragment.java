package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.database.UserManager;
import com.example.plantmanager.databinding.FragmentLoginBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.SqlConnectionManager;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        if (!areUsernameAndPasswordValid(binding.etLoginUsername.getText().toString(),
                binding.etLoginPassword.getText().toString())) {
            Toast.makeText(getContext(), R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("Connect succeeded!");
        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.navigate_from_loginFragment_to_homeFragment);
    }

    private boolean areUsernameAndPasswordValid(String username, String password) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        UserManager userManger = new UserManager();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    this.getString(R.string.db_classes),
                    this.getString(R.string.db_connection_url));
            ArrayList<User> users = userManger.getUsers(databaseConnection);

            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password))
                    return true;
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}