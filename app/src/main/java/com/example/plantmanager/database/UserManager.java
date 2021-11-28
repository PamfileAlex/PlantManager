package com.example.plantmanager.database;

import com.example.plantmanager.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
    public ArrayList<User> getUsers(Connection connection) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        CallableStatement statement = connection.prepareCall("{call spSelectUsers}");
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            User user = new User(resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("email"),
                    resultSet.getString("username"),
                    resultSet.getString("password"));
            users.add(user);
        }

        return users;
    }

    public void insertUser(Connection connection, User user) throws SQLException {
        CallableStatement statement = connection.prepareCall("{call spInsertUser(?, ?, ?, ?, ?)}");

        statement.setString(1, user.getLastName());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getUsername());
        statement.setString(5, user.getPassword());
        
        statement.execute();
    }
}
