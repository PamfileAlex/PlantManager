package com.example.plantmanager.view_models;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.database.CategoryDataAccess;
import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.CurrentUser;

import java.util.ArrayList;

public class ApplicationViewModel extends ViewModel {
    private final ArrayList<Plant> plants;
    private final ArrayList<Category> categories;

    public ApplicationViewModel() {
        this.plants = PlantDataAccess.getPlants(CurrentUser.INSTANCE.getUser().getId());
        categories = CategoryDataAccess.getCategories();
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}