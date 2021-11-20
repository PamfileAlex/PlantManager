package com.example.plantmanager;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plantmanager.databinding.FragmentHomeBinding;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.OnItemListener;
import com.example.plantmanager.utils.PlantsRecyclerAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel mViewModel;

    private final OnItemListener<Plant> onItemListener = new OnItemListener<Plant>() {
        @Override
        public void onItemClick(Plant item, int position) {
            Toast.makeText(getParentFragment().getContext(), "clicked " + position, Toast.LENGTH_SHORT).show();
        }
    };

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.plantList.setAdapter(new PlantsRecyclerAdapter(mViewModel.getPlants(), onItemListener));
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }
}