package com.example.plantmanager.data_access;

import com.example.plantmanager.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDataAccess {
    public static void insertUser(User user) {
        try {
            Connection databaseConnection = DataAccessHelper.getConnection();
            CallableStatement statement = databaseConnection.prepareCall("{call spInsertUser(?, ?, ?, ?, ?, ?)}");

            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setBoolean(6, user.isActive());

            statement.execute();
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String username, String password) {
        User user = null;

        try {
            Connection databaseConnection = DataAccessHelper.getConnection();

            CallableStatement statement = databaseConnection.prepareCall("{call spSelectUserByUsernameAndPassword(?, ?)}");

            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                user = new User(resultSet.getInt("id_user"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("active"));
            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User getUser(String username) {
        User user = null;

        try {
            Connection databaseConnection = DataAccessHelper.getConnection();

            CallableStatement statement = databaseConnection.prepareCall("{call spSelectUserByUsername(?)}");

            statement.setString(1, username);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                user = new User(resultSet.getInt("id_user"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("active"));
            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void updateUser(User user) {
        try {
            Connection databaseConnection = DataAccessHelper.getConnection();

            CallableStatement statement = databaseConnection.prepareCall("{call spUpdateUser(?, ?, ?, ?, ?)}");

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getEmail());
            statement.setBoolean(5, user.isActive());

            statement.execute();

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
