package com.example.plantmanager.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.io.ByteArrayOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlantDataAccess {
    public static ArrayList<Plant> getPlants(int idUser) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        ArrayList<Plant> plants = new ArrayList<>();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);

            CallableStatement statement = databaseConnection.prepareCall("{call spGetPlantsByUserId(?)}");

            statement.setInt(1, idUser);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                byte[] bytes = resultSet.getBytes("image");
                Bitmap image = null;

                if (bytes != null) {
                    image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }

                Plant plant = new Plant(resultSet.getInt("id_plant"),
                        resultSet.getString("name"),
                        image,
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
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);
            CallableStatement statement = databaseConnection.prepareCall("{call spInsertPlant(?, ?, ?, ?, ?)}");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            plant.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();

            statement.setString(1, plant.getName());
            statement.setDate(2, new java.sql.Date(plant.getLastWatered().getTime()));
            statement.setBytes(3, bytes);
            statement.setInt(4, plant.getIdCategory());
            statement.setInt(5, idUser);

            statement.execute();
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
