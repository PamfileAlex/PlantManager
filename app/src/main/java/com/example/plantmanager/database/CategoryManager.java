package com.example.plantmanager.database;

import com.example.plantmanager.models.Category;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryManager {
    public ArrayList<Category> getCategories(Connection connection) throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();

        CallableStatement statement = connection.prepareCall("{call spSelectCategories}");
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Category category = new Category(resultSet.getString("name"));
            categories.add(category);
        }

        return categories;
    }
}
