package com.coderslab;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        final String[] menu = {
                "Pokaż listę użytkowników",
                "Dodaj użytkownika",
                "Zmodyfikuj użytkownika",
                "Usuń użytkownika",
                "Wyjdź"
        };
        chooseAction(menu);
    }
    public static int getInput() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.print("\t!!! Zła wartość. Podaj liczbę !!! ");
        }
        int chosenOption = scan.nextInt();
        return chosenOption;
    }
    public static void chooseAction(String[] menu) throws SQLException {
        showMenu(menu);
        int chosenOption = getInput();

        switch (chosenOption) {
            case 1: User.listAllUsers();
                    break;
            case 2: User createdUser = User.createUser();
                    if(createdUser != null) System.out.println("\tDodano użytkownika: " + createdUser.getInfo() + "\n");
                    break;
            case 3: User modifiedUser = User.modifyUser();
                    if(modifiedUser != null) System.out.println("\tNowe dane: " + modifiedUser.getInfo() + "\n");
                    break;
            case 4: User removedUser = User.removeUser();
                    if(removedUser != null) System.out.println("\tUżytkownik: " + removedUser.getInfo() + " został usunięty.\n");
                    break;
            case 5: return;
            default:
                    System.out.println("\t!!! Zła wartość. Podaj liczbę !!!\n");
                    break;
        }
        chooseAction(menu);
    }
    public static void showMenu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            System.out.println("\t" + (i+1) + ". " + menu[i]);
        }
        System.out.print("\tWybierz opcję: ");
    }
}
