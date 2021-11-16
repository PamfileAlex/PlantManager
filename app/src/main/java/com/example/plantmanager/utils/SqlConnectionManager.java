package com.example.plantmanager.utils;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plantmanager.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlConnectionManager extends AppCompatActivity {
    @SuppressLint("NewApi")
    public Connection getSqlConnection(String classes, String url) {
        Connection connection = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(classes);
            connection = DriverManager.getConnection(url);
            System.out.println("SUCCESS");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILURE");
        }

        return connection;
    }

    public void printUsers(Connection connection) throws Exception {
        PreparedStatement statement = connection.prepareStatement("EXEC selectAllUsers");
        ArrayList list = new ArrayList();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("first_name"));
            System.out.println(rs.getString("first_name"));
        }

    }
}
