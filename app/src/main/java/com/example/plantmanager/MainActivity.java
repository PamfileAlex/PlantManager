package com.example.plantmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.plantmanager.models.ResourceProvider;

public class MainActivity extends AppCompatActivity {
    public static ResourceProvider resourceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (resourceProvider == null)
            resourceProvider = new ResourceProvider(this);
    }
}