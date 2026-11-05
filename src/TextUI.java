import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    Scanner scan = new Scanner(System.in);

    public void userMessage(String msg) {
        System.out.println(msg);}

    public String promtUser(String promt){
        System.out.println(promt);
        String input = scan.nextLine();
        return input;
    }


    public boolean promptBineary(String msg){
        String input = promtUser(msg).toUpperCase();
        if (input.equals("Y")){
            return true;
        }
        if (input.equals("N")){
            return false;
        }
        return promptBineary(msg);
    }



    public int promptNumeric(String msg) {
        System.out.println(msg);
        int input = 0;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Ugyldigt input, skriv et tal i stedet");
            input = promptNumeric(msg);
        }
        return input;
    }

}
