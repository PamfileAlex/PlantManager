package com.example.plantmanager.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.R;
import com.example.plantmanager.database.UserDataAccess;
import com.example.plantmanager.databinding.ActivityLoginBinding;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.CurrentUser;
import com.example.plantmanager.view_models.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(LoginActivity.this).get(UserViewModel.class);
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

        userViewModel.setUser(user);
        CurrentUser.INSTANCE.setUser(user);

        startActivity(new Intent(getApplicationContext(), ApplicationActivity.class));
        //NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.navigate_from_loginFragment_to_mainFragment);
    }
}