package org.example.view;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner console = new Scanner(System.in);

    public static String readAirlineName() {
        System.out.print("Введите название авиакомпании: ");
        return console.nextLine();
    }
}