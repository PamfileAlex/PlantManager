package com.example.plantmanager.database;

import com.example.plantmanager.models.Plant;
import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlantDataAccess {
    private static ArrayList<Plant> getPlants(Connection connection) throws SQLException {
        ArrayList<Plant> plants = new ArrayList<>();

        CallableStatement statement = connection.prepareCall("{call spSelectPlants}");
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Plant plant = new Plant(resultSet.getInt("id_plant"),
                    resultSet.getString("name"),
                    resultSet.getString("image_url"),
                    resultSet.getDate("last_watered"));
            plants.add(plant);
        }

        return plants;
    }

    public static ArrayList<Plant> getPlants() {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        PlantDataAccess plantManager = new PlantDataAccess();
        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);

            ArrayList<Plant> plants = plantManager.getPlants(databaseConnection);
            databaseConnection.close();

            return plants;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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
                        resultSet.getDate("last_watered"));
                plants.add(plant);
            }
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plants;
    }
}