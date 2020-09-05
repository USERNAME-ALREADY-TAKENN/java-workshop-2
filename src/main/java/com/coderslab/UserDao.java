package com.coderslab;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {
    private static final String QUERY_ADD = "INSERT INTO users(name, password, email) VALUES (?,?,?)";
    private static final String QUERY_REMOVE = "DELETE FROM users WHERE id=?";
    private static final String QUERY_UPDATE_ALL = "UPDATE users SET name=?, password=?, email=? WHERE id=?";
    private static final String queryUpdateOne = "UPDATE users SET ?=? WHERE ?=?";
    private static final String QUERY_GET_USER = "SELECT * FROM users WHERE id = ?";
    private static final String QUERY_GET_PASSWORD = "SELECT password FROM users WHERE id=?";
    private static final String QUERY_GET_ALL_USERS = "SELECT * FROM users";

    public static User create(User user) {
        try (Connection conn = DBUtil.connect()) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            PreparedStatement statement = conn.prepareStatement(QUERY_ADD, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, hashedPassword);
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void update(User user) {
        try (Connection conn = DBUtil.connect()) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE_ALL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, hashedPassword);
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static User findUser(int userId) {
        try (Connection conn = DBUtil.connect();) {
            PreparedStatement stmt = conn.prepareStatement(QUERY_GET_USER);
            stmt.setInt(1, userId);
            //Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            User user = new User(name, password, email);
            user.setId(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeUser(int id) throws SQLException {

        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(QUERY_REMOVE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserPassword(int id) throws SQLException {

        try (Connection conn = DBUtil.connect();) {
            PreparedStatement stmt = conn.prepareStatement(QUERY_GET_PASSWORD);
            stmt.setInt(1, id);
            //Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User[] getAllUsers() {
        User[] users;

        try (Connection conn = DBUtil.connect();) {
//            PreparedStatement stmt = conn.prepareStatement(QUERY_GET_ALL_USERS);
//            stmt.setInt(1, userId);
            PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL_USERS, Statement.RETURN_GENERATED_KEYS);


            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_GET_ALL_USERS);
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            User user = new User(name, password, email);
            user.setId(id);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
