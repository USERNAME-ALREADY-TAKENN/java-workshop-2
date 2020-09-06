package com.coderslab;
import java.sql.*;

public class DBUtil {
    private static final String DB_USER = "javatest";
    private static final String DB_PASSWORD = "javatest";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?serverTimezone=UTC";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
