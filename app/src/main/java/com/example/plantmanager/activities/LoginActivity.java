package com.example.plantmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.ActivityLoginBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.CurrentUser;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        binding.tvLoginRegister.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        binding.btnLogin.setOnClickListener(view -> manageLogin());

        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_login);
    }

    private void manageLogin() {
        User user = UserDataAccess.getUser(binding.etLoginUsername.getText().toString(),
                binding.etLoginPassword.getText().toString());
        if (user == null) {
            Toast.makeText(LoginActivity.this, R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        CurrentUser.INSTANCE.setUser(user);

        startActivity(new Intent(getApplicationContext(), ApplicationActivity.class));
    }
}