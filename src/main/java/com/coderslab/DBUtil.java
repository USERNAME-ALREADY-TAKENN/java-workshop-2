package com.coderslab;
import java.sql.*;

public class DBUtil {
    private static final String DB_USER = "javatest";
    private static final String DB_PASSWORD = "javatest";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?serverTimezone=UTC";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void insert(Connection conn, String query, String... params) {
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printCellsFormatted(Connection conn, String query, String averageRating, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, averageRating);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                for (String param : columnNames) {
                    System.out.print(resultSet.getString(param) + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
