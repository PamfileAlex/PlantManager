package com.example.plantmanager.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.data_access.UserDataAccess;
import com.example.plantmanager.databinding.ActivityLoginBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.LoggedUserManager;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        binding.tvLoginRegister.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        binding.btnLogin.setOnClickListener(view -> manageLogin());

        setContentView(binding.getRoot());
    }

    private void manageLogin() {
        String username = binding.etLoginUsername.getText().toString();
        String password = binding.etLoginPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, R.string.incomplete_form, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = UserDataAccess.getUser(username, password);
        if (user == null) {
            Toast.makeText(LoginActivity.this, R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!user.isActive()) {
            Toast.makeText(LoginActivity.this, R.string.inactive_user, Toast.LENGTH_SHORT).show();
            return;
        }

        LoggedUserManager.INSTANCE.login(user);

        startActivity(new Intent(getApplicationContext(), ApplicationActivity.class));
    }
}