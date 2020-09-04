package com.coderslab;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Dodaj nowego użytkonika");
        System.out.print("Podaj nazwę: ");
        String name = scan.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scan.nextLine();
        System.out.print("Podaj email: ");
        String email = scan.nextLine();

        //User user = new User(name, password, email);


    }
}
