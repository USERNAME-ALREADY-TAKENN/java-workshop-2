package com.coderslab;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {
    private static final String QUERY_ADD = "INSERT INTO users(name, password, email) VALUES (?,?,?)";
    //private static final String queryRemove = "DELETE FROM users WHERE ?=?";
    private static final String queryUpdateAll = "UPDATE users SET name=?, password=?, email=? WHERE id=?";
    private static final String queryUpdateOne = "UPDATE users SET ?=? WHERE ?=?";
    private static final String queryGetUser = "SELECT * FROM users WHERE id = ?";

    public static User create(User user) {
        try (Connection conn = DBUtil.connect()) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            PreparedStatement statement = conn.prepareStatement(QUERY_ADD, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashedPassword);
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
    public static User update(User user) {
        try (Connection conn = DBUtil.connect()) {
            // HASHOWAC HASLA?
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            PreparedStatement statement = conn.prepareStatement(queryUpdateAll, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashedPassword);
            statement.executeUpdate();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId) {
        try (Connection conn = DBUtil.connect();) {
            PreparedStatement stmt = conn.prepareStatement(queryGetUser);
            stmt.setInt(1, userId);
            //Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryGetUser);
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
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";
    public static void remove(Connection conn, String tableName, int id) {

    }

//    public static void getUserField(String column, String value) throws SQLException {
//
//        String query = "SELECT * FROM users WHERE ?=?";
//
//        try (Connection conn = DBUtil.connect();) {
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, column);
//            stmt.setString(2, value);
//            //Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            rs.next();
//            return rs.getString(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //if (BCrypt.checkpw(candidate, hashed)) {
    //    System.out.println("Ok");
    //} else {
    //    System.out.println("It does not match");
    //}
}
