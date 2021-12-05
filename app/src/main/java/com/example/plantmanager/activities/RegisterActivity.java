package com.example.plantmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.ActivityRegisterBinding;
import com.example.plantmanager.models.User;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        binding.btnRegister.setOnClickListener(view -> manageRegister());

        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_register);
    }

    private void manageRegister() {
        String lastName = binding.etRegisterLastName.getText().toString();
        String firstName = binding.etRegisterFirstName.getText().toString();
        String email = binding.etRegisterEmail.getText().toString();
        String username = binding.etRegisterUsername.getText().toString();
        String password = binding.etRegisterPassword.getText().toString();
        String confirmPassword = binding.etRegisterConfirmPassword.getText().toString();

        if (lastName.equals("") || firstName.equals("") || email.equals("") ||
                username.equals("") || password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(RegisterActivity.this, R.string.empty_field, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkPassword(password, confirmPassword)) {
            Toast.makeText(RegisterActivity.this, R.string.not_same_password, Toast.LENGTH_SHORT).show();
            return;
        }

        if (UserDataAccess.getUser(username) != null) {
            Toast.makeText(RegisterActivity.this, R.string.username_already_exists, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(lastName, firstName, email, username, password);
        UserDataAccess.insertUser(user);

        Toast.makeText(RegisterActivity.this, R.string.register_succeeded, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    private boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}