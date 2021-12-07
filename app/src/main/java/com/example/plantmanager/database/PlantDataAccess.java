package com.example.plantmanager.database;

import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.BitmapUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class PlantDataAccess {
    public static ArrayList<Plant> getPlants(int idUser) {
        ArrayList<Plant> plants = new ArrayList<>();

        try {
            Connection databaseConnection = DataAccessHelper.getConnection();

            CallableStatement statement = databaseConnection.prepareCall("{call spGetPlantsByUserId(?)}");

            statement.setInt(1, idUser);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Plant plant = new Plant(resultSet.getInt("id_plant"),
                        resultSet.getString("name"),
                        BitmapUtils.fromBytes(resultSet.getBytes("image")),
                        resultSet.getDate("last_watered"),
                        resultSet.getInt("id_category"));
                plants.add(plant);
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plants;
    }

    public static void insertPlant(Plant plant, int idUser) {
        try {
            Connection databaseConnection = DataAccessHelper.getConnection();
            CallableStatement statement = databaseConnection.prepareCall("{call spInsertPlant(?, ?, ?, ?, ?)}");

            statement.setString(1, plant.getName());
            statement.setDate(2, new java.sql.Date(plant.getLastWatered().getTime()));
            statement.setBytes(3, BitmapUtils.fromBitmap(plant.getImage()));
            statement.setInt(4, plant.getIdCategory());
            statement.setInt(5, idUser);

            statement.execute();
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
