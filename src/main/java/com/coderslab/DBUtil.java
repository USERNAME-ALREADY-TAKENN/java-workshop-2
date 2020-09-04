package com.coderslab;
import java.sql.*;

public class DBUtil {
    private static final String DB_USER = "javatest";
    private static final String DB_PASSWORD = "javatest";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?serverTimezone=UTC";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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

    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String param : columnNames) {
                    System.out.println(resultSet.getString(param));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";
    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
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

    public static String getData(Connection conn, String query, String column, String value) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, column);
            stmt.setString(2, value);
            //Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
