package com.example.plantmanager.database;

import com.example.plantmanager.models.Plant;
import com.example.plantmanager.utils.SqlConnectionManager;

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
                Plant plant = new Plant(resultSet.getInt("id_plant"),
                        resultSet.getString("name"),
                        resultSet.getString("image_url"),
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

    private static void insertPlant(Connection connection, Plant plant, int idUser) throws SQLException {
        CallableStatement statement = connection.prepareCall("{call spInsertPlant(?, ?, ?, ?, ?)}");

        statement.setString(1, plant.getName());
        statement.setDate(2, (Date) plant.getLastWatered());
        statement.setString(3, plant.getImagePath());
        statement.setInt(4, plant.getIdCategory());
        statement.setInt(5, idUser);

        statement.execute();
    }
}
