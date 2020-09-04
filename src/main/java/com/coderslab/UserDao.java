package com.coderslab;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private static String queryAdd = "INSERT INTO users(name, password, email) VALUES (?,?,?)";
    //private String queryRemove = "DELETE FROM users WHERE ?=?";
    private String queryUpdateAll = "UPDATE users SET name=?, password=?, email=? WHERE ?=?";
    private String queryUpdateOne = "UPDATE users SET ?=? WHERE ?=?";

    public static void addUser(User user) throws SQLException {
        Connection conn = DBUtil.connect();
        //DBUtil.insert(conn, queryAdd, name, password, email);
    }
    public static void removeUser(int id) throws SQLException {
        Connection conn = DBUtil.connect();
        DBUtil.remove(conn,"users", id);
    }
    public static void getUserId(String column, String value) throws SQLException {
        Connection conn = DBUtil.connect();
        String query = "SELECT * FROM users WHERE ?=?";
        DBUtil.getData(conn, query, column, value);
    }

}
