package com.coderslab;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public static User createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\tDodaj nowego użytkonika");
        System.out.print("\tPodaj nazwę: ");
        String name = scan.nextLine();
        System.out.print("\tPodaj hasło: ");
        String password = scan.nextLine();
        System.out.print("\tPodaj email: ");
        String email = scan.nextLine();

        User user = new User(name, password, email);
        user = UserDao.create(user);
        return user;
    }

    public static User removeUser() throws SQLException {
        System.out.print("\tPodaj ID użytkownika: ");
        int choiceId = Main.getInput();
        User user = UserDao.findUser(choiceId);
        if(user != null) UserDao.removeUser(user.getId());
        else System.out.println("\t!!! Brak takiego użytkownika !!!\n");
        return user;
    }

    public static User modifyUser() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.print("\tPodaj ID użytkownika: ");
        int choiceId = Main.getInput();
        User user = UserDao.findUser(choiceId);
        if (user == null) {
            System.out.println("\t!!! Brak takiego użytkownika !!!\n");
            return null;
        }
        System.out.print("\n\tWybrano: " + user.getInfo());
        System.out.print("\n\tPodaj hasło, aby zmienić dane: ");
        String password = scan.nextLine();

        String hashedPassword = user.getPasswordFromDB();
        try {
            BCrypt.checkpw(password, hashedPassword);
            //poniższe wyrzuca exception
//            if (!BCrypt.checkpw(password, hashedPassword)) {
//                System.out.println("\tBłędne hasło! ");
//                return null;
//            }
        } catch (IllegalArgumentException e) {
            System.out.println("\t!!! BŁĘDNE HASŁO !!! \n");
            return null;
        }
        System.out.println("\tPodaj nowe dane");
        System.out.print("\tPodaj nową nazwę: ");
        user.name = scan.nextLine();
        System.out.print("\tPodaj nowe hasło: ");
        user.password = scan.nextLine();
        System.out.print("\tPodaj nowy email: ");
        user.email = scan.nextLine();

        UserDao.update(user);
        return user;
    }

    public static void listAllUsers() throws SQLException {
        User[] users = UserDao.getAllUsers();

        System.out.println("\n\tID - NAME - (EMAIL)");
        for (int i = 0; i < users.length; i++) {
            System.out.println("\t"+String.valueOf(users[i].id) + " - " + users[i].name + " (" + users[i].email+")");
        }
        System.out.println();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getPasswordFromDB() throws SQLException {
        String password = UserDao.getUserPassword(this.id);
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

    public String getInfo () {
        return this.id + ". nazwa(" + this.name + "), email(" + this.email + ")";
    }
}
