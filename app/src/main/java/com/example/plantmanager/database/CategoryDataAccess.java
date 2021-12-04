package com.example.plantmanager.database;

import com.example.plantmanager.models.Category;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDataAccess {
    public static ArrayList<Category> getCategories() {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        ArrayList<Category> categories = new ArrayList<>();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);

            CallableStatement statement = databaseConnection.prepareCall("{call spSelectCategories}");
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("id_category"),
                        resultSet.getString("name"));
                categories.add(category);
            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
