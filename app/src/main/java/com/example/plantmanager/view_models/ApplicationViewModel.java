package com.example.plantmanager.view_models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.example.plantmanager.database.CategoryDataAccess;
import com.example.plantmanager.database.PlantDataAccess;
import com.example.plantmanager.models.Category;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.CurrentUser;
import com.example.plantmanager.utils.PlantsRecyclerAdapter;

import java.util.ArrayList;

public class ApplicationViewModel extends ViewModel {
    private final ArrayList<Plant> allPlants;
    private final ArrayList<Plant> plants;
    private final ArrayList<Category> categories;
    private Category currentCategory;

    private PlantsRecyclerAdapter plantsRecyclerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ApplicationViewModel() {
        this.allPlants = PlantDataAccess.getPlants(CurrentUser.INSTANCE.getUser().getId());
        //plants = allPlants;
        plants = new ArrayList<>();
        categories = CategoryDataAccess.getCategories();
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
        plants.clear();
        if (currentCategory.getId() == 1) {
            plants.addAll(allPlants);
            notifyPlantsRecyclerAdapter();
            return;
        }
        for (Plant plant : allPlants) {
            if (currentCategory.getId() == plant.getIdCategory()) {
                plants.add(plant);
            }
        }
        notifyPlantsRecyclerAdapter();
    }

    public void notifyPlantsRecyclerAdapter() {
        if (plantsRecyclerAdapter != null) {
            plantsRecyclerAdapter.notifyDataSetChanged();
        }
    }

    public PlantsRecyclerAdapter getPlantsRecyclerAdapter() {
        return plantsRecyclerAdapter;
    }

    public void setPlantsRecyclerAdapter(PlantsRecyclerAdapter plantsRecyclerAdapter) {
        this.plantsRecyclerAdapter = plantsRecyclerAdapter;
    }

    public void addPlant(Plant plant){
        allPlants.add(plant);
        notifyPlantsRecyclerAdapter();
    }
}