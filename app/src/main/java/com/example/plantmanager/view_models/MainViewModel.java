package com.example.plantmanager.view_models;

import android.util.Pair;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.R;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class MainViewModel extends ViewModel {
    private int selectedNavigationBarFragment = R.id.home;
    private final HashMap<Pair<Integer, Integer>, Integer> navigation;
//    private HashMap<Pair<Integer,Integer>,Integer> navigation= new HashMap<>(Map.ofEntries(
//            new AbstractMap.SimpleEntry<>(new Pair<>(R.id.home, R.id.add_plant), R.id.navigate_from_homeFragment_to_addPlantFragment),
//            new AbstractMap.SimpleEntry<>(new Pair<>(R.id.add_plant, R.id.home), R.id.navigate_from_addPlantFragment_to_homeFragment)
//            ));

    public MainViewModel() {
        navigation = new HashMap<>();
        navigation.put(new Pair<>(R.id.home, R.id.add_plant), R.id.navigate_from_homeFragment_to_addPlantFragment);
        navigation.put(new Pair<>(R.id.add_plant, R.id.home), R.id.navigate_from_addPlantFragment_to_homeFragment);
    }

    public int getSelectedNavigationBarFragment() {
        return selectedNavigationBarFragment;
    }

    public void setSelectedNavigationBarFragment(int selectedNavigationBarFragment) {
        this.selectedNavigationBarFragment = selectedNavigationBarFragment;
    }

    public int getNavigation(int to) {
        return navigation.get(new Pair<>(selectedNavigationBarFragment, to));
    }
}
