import org.junit.jupiter.api.Test;
import util.FileIO;
import util.TextUI;

import java.util.ArrayList;

class StreamingServiceTest {

    @Test
    void registerUserTest() {
        switch (TextUI.promptNumeric("Log in(1) or create user(2)?")){
            case 1:
                userLogInTest();
                break;
            case 2:
                createUserTest();
                break;
            default:
                System.out.println("Illegal input, try again");
                registerUserTest();
            }
    }

    @Test
    void userLogInTest() {
        String username = TextUI.promptText("Enter your username: ");
        String password = TextUI.promptText("Enter your password: ");
        String correctPassword = FileIO.readData("data/"+username+".csv").getFirst();
            if (password.equals(correctPassword)){
                System.out.println("Password is correct!");
            }
    }

    @Test
    void createUserTest() {
            String username = TextUI.promptText("Enter your username: ");
            String password = TextUI.promptText("Enter your password: ");
            while (password.length() <= 6){
                System.out.println("Password is too short, try again");
                password = TextUI.promptText("Enter your password: ");
            }
            ArrayList<String> data = new ArrayList<>();
            data.add(password);
            if(FileIO.createFile("data/"+username+".csv")){
                FileIO.saveData(("data/"+username+".csv"), "Password:", data);
                userLogInTest();
            }
    }
}