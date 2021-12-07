package com.example.plantmanager.database;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataAccessHelper {
    private final static String dbClasses = "net.sourceforge.jtds.jdbc.Driver";
    // ROBERTA
//    final static String dbConnectionUrl ="jdbc:jtds:sqlserver://172.27.160.1:1433;databaseName=PlantManager;user=sa;password=1234;instance=SQLEXPRESS;";
    // ALEX
    private final static String dbConnectionUrl ="jdbc:jtds:sqlserver://192.168.1.139:1433;databaseName=PlantManager;user=admin;password=1q2w3e;instance=SQLEXPRESS;";

    public static Connection getConnection() {
        Connection connection = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(dbClasses);
            connection = DriverManager.getConnection(dbConnectionUrl);
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
}
