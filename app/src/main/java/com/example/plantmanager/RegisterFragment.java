package com.example.plantmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        if (existsUsername(username)) {
            Toast.makeText(getContext(), R.string.username_already_exists, Toast.LENGTH_SHORT).show();
            return;
        }

        if(lastName.equals("") || firstName.equals("") || email.equals("") ||
        username.equals("") || password.equals("") || confirmPassword.equals("")){
            Toast.makeText(getContext(), R.string.empty_field, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(lastName, firstName, email, username, password);
        addUser(user);

        Toast.makeText(getContext(), R.string.register_succeeded, Toast.LENGTH_SHORT).show();

        NavHostFragment.findNavController(RegisterFragment.this)
                .navigate(R.id.navigate_from_registerFragment_to_loginFragment);
    }

    private boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean existsUsername(String username) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        UserManager userManger = new UserManager();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    this.getString(R.string.db_classes),
                    this.getString(R.string.db_connection_url));
            ArrayList<User> users = userManger.getUsers(databaseConnection);

            for (User user : users) {
                if (user.getUsername().equals(username))
                    return true;
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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