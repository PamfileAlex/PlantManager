package com.example.plantmanager.data_access;

import com.example.plantmanager.models.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDataAccess {
    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            Connection databaseConnection = DataAccessHelper.getConnection();

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
