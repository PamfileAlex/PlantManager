package com.example.plantmanager.view_models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.example.plantmanager.database.CategoryDataAccess;
import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.CurrentUser;

import java.util.ArrayList;

public class ApplicationViewModel extends ViewModel {
    private final ArrayList<Plant> allPlants;
    private final ArrayList<Plant> plants;
    private final ArrayList<Category> categories;
    private Category currentCategory;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ApplicationViewModel() {
        this.allPlants = PlantDataAccess.getPlants(CurrentUser.INSTANCE.getUser().getId());
        plants = allPlants;
        categories = CategoryDataAccess.getCategories();
        //currentCategory = categories.get(0);
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }
}