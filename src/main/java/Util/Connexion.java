package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    // private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // private static final String URL = "jdbc:mysql://localhost:3306/GestionWebDynamique";
    // private static final String USER = "root";
    // private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.80.237.53:3306/db_s2_ETU003263";
    private static final String USER = "ETU003263";
    private static final String PASSWORD = "SKS0gfBf";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Driver MySQL introuvable: " + e.getMessage());
        }
    }
    public static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}