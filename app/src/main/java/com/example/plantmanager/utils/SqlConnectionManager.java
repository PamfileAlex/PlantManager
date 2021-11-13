package com.example.plantmanager.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnectionManager {
    Connection con;
    String userName, password, ip, port, database;

    @SuppressLint("NewApi")
    public void getSqlConnectionManager() throws ClassNotFoundException {
//        String _user = "sa";
//        String _pass = "1234";
//        String _DB = "PlantManager";
//        String _server = "172.31.224.1";
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                .permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        Connection conn = null;
//        String ConnURL = null;
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
//                    + "databaseName=" + _DB + ";user=" + _user + ";password="
//                    + _pass + ";";
//            conn = DriverManager.getConnection(ConnURL);
//        } catch (SQLException se) {
//            Log.e("ERRO", se.getMessage());
//        } catch (ClassNotFoundException e) {
//            Log.e("ERRO", e.getMessage());
//        } catch (Exception e) {
//            Log.e("ERRO", e.getMessage());
//        }
//        return conn;


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String ip = "172.31.144.1";
        String port = "1433";
        String Classes = "net.sourceforge.jtds.jdbc.Driver";
        String database = "PlantManager";
        String username = "sa";
        String password = "1234";
       // String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;
        String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";"
                + "databaseName=" + database + ";user=" + username + ";password="
                + password + ";instance=SQLEXPRESS;" ;

        Class.forName(Classes);

        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            String SQL = "select * from [User]";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(rs.getString("first_name"));
            }
            System.out.println("Success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILURE");
        }

        //String connectionUrl = "jdbc:sqlserver://172.31.224.1\\LAPTOP-1MG32L2A\\SQLEXPRESS"+serverport+";databaseName="+dbName+"";

        // String connectionUrl = "jdbc:sqlserver://"+"172.31.224.1"+"\\LAPTOP-1MG32L2A\\SQLEXPRESS"+serverport+";databaseName="+dbName+"";
        // String connectionUrl = "jdbc:sqlserver://172.31.224.1:1433;databaseName=PlantManager;user=sa;password=1234";

//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//            Statement stmt = con.createStatement();
//            String SQL = "SELECT * FROM USER";
//            ResultSet rs = stmt.executeQuery(SQL);
//
//            while (rs.next()) {
//                System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
