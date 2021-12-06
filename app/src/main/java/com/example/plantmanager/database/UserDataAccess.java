package com.example.plantmanager.database;

import com.example.plantmanager.models.User;
import com.example.plantmanager.utils.SqlConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDataAccess {
    public static ArrayList<User> getUsers(Connection connection) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        CallableStatement statement = connection.prepareCall("{call spSelectUsers}");
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            User user = new User(resultSet.getInt("id_user"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("email"),
                    resultSet.getString("username"),
                    resultSet.getString("password"));
            users.add(user);
        }

        return users;
    }

    private static void insertUser(Connection connection, User user) throws SQLException {
        CallableStatement statement = connection.prepareCall("{call spInsertUser(?, ?, ?, ?, ?)}");

        statement.setString(1, user.getLastName());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getUsername());
        statement.setString(5, user.getPassword());

        statement.execute();
    }

    public static void insertUser(User user){
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        UserDataAccess userManger = new UserDataAccess();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);
            userManger.insertUser(databaseConnection, user);
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String username, String password) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        User user = null;

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);

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
                        resultSet.getString("password"));
            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static User getUser(String username) {
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        User user = null;

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);

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
                        resultSet.getString("password"));
            }

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void updateUser(User user){
        SqlConnectionManager sqlConnectionManager = new SqlConnectionManager();
        UserDataAccess userManger = new UserDataAccess();

        try {
            Connection databaseConnection = sqlConnectionManager.getSqlConnection(
                    DataAccessHelper.dbClasses,
                    DataAccessHelper.dbConnectionUrl);

            CallableStatement statement = databaseConnection.prepareCall("{call spUpdateUser(?, ?, ?, ?)}");

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getEmail());

            statement.execute();

            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
