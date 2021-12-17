package com.example.plantmanager.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.plantmanager.R;
import com.example.plantmanager.databinding.ActivityApplicationBinding;
import com.example.plantmanager.views.fragments.AddPlantFragment;
import com.example.plantmanager.views.fragments.HomeFragment;
import com.example.plantmanager.views.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class ApplicationActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private ActivityApplicationBinding binding;
    private final HomeFragment homeFragment = new HomeFragment();
    private final AddPlantFragment addPlantFragment = new AddPlantFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplicationBinding.inflate(getLayoutInflater());

        binding.bottomNavigation.setOnItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.home);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, homeFragment).commit();

        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_application);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new HomeFragment()).commit();
                break;
            case R.id.add_plant:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new AddPlantFragment()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new ProfileFragment()).commit();
                break;
            default:
                break;
        }
        return true;
    }
}