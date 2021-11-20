package com.example.plantmanager.database;

import com.example.plantmanager.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
    public ArrayList<User> getUsers(Connection connection) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("EXEC selectAllUsers");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            User user = new User(rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"));
            users.add(user);
        }

        return users;
    }

    public void insertUser(Connection connection, User user) throws SQLException {
        CallableStatement statement = connection.prepareCall("{call insertUser(?, ?, ?, ?, ?)}");
        statement.setString(1, user.getLastName());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getUsername());
        statement.setString(5, user.getPassword());
        statement.execute();
    }
}
