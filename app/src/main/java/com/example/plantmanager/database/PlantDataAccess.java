package com.example.plantmanager.database;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.BitmapUtils;
import com.example.plantmanager.utils.LocalDateConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.*;

public final class PlantDataAccess {
    @RequiresApi(api = Build.VERSION_CODES.O)
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
                        resultSet.getInt("id_category"),
                        resultSet.getString("name"),
                        BitmapUtils.fromBytes(resultSet.getBytes("image")),
                        LocalDateConverter.fromSqlDate(resultSet.getDate("last_watered")),
                        LocalDateConverter.fromSqlDate(resultSet.getDate("next_water")),
                        resultSet.getTime("time").toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
                plants.add(plant);
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plants;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insertPlant(Plant plant, int idUser) {
        try {
            Connection databaseConnection = DataAccessHelper.getConnection();
            CallableStatement statement = databaseConnection.prepareCall("{call spInsertPlant(?, ?, ?, ?, ?, ?, ?, ?)}");

            statement.setInt(1, idUser);
            statement.setInt(2, plant.getIdCategory());
            statement.setString(3, plant.getName());
            statement.setBytes(4, BitmapUtils.fromBitmap(plant.getImage()));
            statement.setDate(5, LocalDateConverter.toSqlDate(plant.getLastWatered()));
            statement.setDate(6, LocalDateConverter.toSqlDate(plant.getNextWater()));
            statement.setTime(7, new java.sql.Time(plant.getTime().getHour(), plant.getTime().getMinute(), 0));
            statement.setBoolean(8, true);

            statement.execute();
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
