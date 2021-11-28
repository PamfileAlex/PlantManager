package com.example.plantmanager;

import androidx.lifecycle.ViewModel;

import com.example.plantmanager.database.PlantManager;
import com.example.plantmanager.models.Plant;
import com.example.plantmanager.models.ResourceProvider;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final ArrayList<Plant> plants;

    private ResourceProvider resourceProvider;

    public HomeViewModel() {
        this.resourceProvider = MainActivity.resourceProvider;
        this.plants = new ArrayList<>();
//        this.plants.add(new Plant(1, "Plant1"));
//        this.plants.add(new Plant(2, "Plant2"));
//        this.plants.add(new Plant(3, "Plant3"));

        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        PlantManager plantManager = new PlantManager();
        try {
            System.out.println("connection");
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    this.resourceProvider.getString(R.string.db_classes),
                    this.resourceProvider.getString(R.string.db_connection_url));

            System.out.println("connection succeeded");
            ArrayList<Plant> plants = plantManager.getPlants(databaseConnection);

            for (Plant plant: plants) {
               this.plants.add(plant);
            }
            databaseConnection.close();
        } catch (SQLException e) {
            System.out.println("connection failed");
            e.printStackTrace();
        }
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
}