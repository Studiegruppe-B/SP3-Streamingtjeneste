package util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    private static final Scanner scan = new Scanner(System.in);

    public static void userMessage(String msg) {
        System.out.println(msg);
    }

    public static String promptText(String prompt){
        System.out.println(prompt);
        return scan.nextLine();
    }

    public static  boolean promptBinary(String msg){
        String input = promptText(msg).toUpperCase();
        if (input.equals("Y")){
            return true;
        }
        if (input.equals("N")){
            return false;
        }
        return promptBinary(msg);
    }

    public static int promptNumeric(String msg) {
        System.out.println(msg);
        int input = 0;
        try {
            input = scan.nextInt();
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Write a number");
            input = promptNumeric(msg);
        }

        scan.nextLine(); //scannerbug flush

        return input;
    }

    public ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg) {
        ArrayList<String> choices = new ArrayList<>();

        while (choices.size() < limit) {
            int input = promptNumeric(msg);
            if (input > 0 && input <= options.size()) {
                String choice = options.get(input - 1);
                choices.add(choice);
            } else {
                System.out.println("Invalid input");
            }
        }
        return choices;
    }

    public void displayList(ArrayList<String> list, String msg, boolean numbered) {//parameter der forstæller OM listen er nummereret eller ej.
        System.out.println(msg);
        for (int i = 0; i < list.size(); i++) {
            if (numbered) {
                System.out.println(i + 1 + ". " + list.get(i));
                continue;
            }
            System.out.println(list.get(i));
        }
    }

    public int promptMenuChoice(ArrayList<String> menu, String msg) {
        displayList(menu, msg, true);
        return promptNumeric("Write a number");
    }
}
