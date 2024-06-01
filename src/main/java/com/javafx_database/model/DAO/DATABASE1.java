package com.javafx_database.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATABASE1 {
    private static String url = "jdbc:mysql://localhost:3306/students";
    private static String user = "root";
    private static String password = "2132";

    private static DATABASE1 database;
    private static Connection connection;

    private DATABASE1(){
    };

    public static DATABASE1 getDatabase(){
        loadMysql();
        return database;
    }
    private static void loadMysql() {
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("database baglantisi kuruldu");
        } catch (SQLException e) {
            System.out.println("bağlantı hatasi : " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
