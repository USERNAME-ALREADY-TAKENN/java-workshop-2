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
    public static int getInput(int menuLength) {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.print("\tZła wartość. Podaj liczbę pomiędzy 1 a " + (menuLength) + ": ");
        }
        int chosenOption = scan.nextInt();
        return chosenOption;
    }
    public static void chooseAction(String[] menu) throws SQLException {

        showMenu(menu);
        int chosenOption = getInput(menu.length);

        switch (chosenOption) {
            case 1:

            case 2: System.out.println("\tDodano użytkownika: " + User.createUser().getInfo() + "\n");
                break;
            case 3: System.out.println("\tNowe dane: " + User.modifyUser().getInfo() + "\n");
                break;
            case 4: System.out.println("\tUżytkownik: " + User.removeUser().getInfo() + " został usunięty.\n");
                break;
            default:
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
//    public static void showList (String[] menu) {
//        int lineLength = 50;
//        String leftDecorator = "\t█\t";
//        String rightDecorator = "█";
//        String topDecorator = "█";
//        String bottomDecorator = "█";
//
//        System.out.println("\n\t" + fill("", lineLength + 3, topDecorator));
//        for (int i = 0; i < dataList.length; i++) {
//            String filledLine = fill(leftDecorator + (i+1) + ". " + dataList[i], lineLength + rightDecorator.length(), " ");
//            System.out.print(filledLine + rightDecorator + "\n");
//        }
//        System.out.println("\t" + fill("", lineLength + 3, bottomDecorator));
//
//        showMenu(menu);

//    }
//
//    public static String fill(String toFill, int lenghtMatch, String fillString) {
//        lenghtMatch = lenghtMatch - toFill.length();
//        String filler = new String(new char[lenghtMatch]).replace("\0", fillString);
//        String filledString = toFill + filler;
//        return filledString;
//    }
}
