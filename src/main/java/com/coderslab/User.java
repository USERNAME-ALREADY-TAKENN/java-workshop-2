package com.coderslab;

import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email) throws SQLException {
        this.name = name;
        this.password = password;
        this.email = email;
        UserDao.addUser(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
