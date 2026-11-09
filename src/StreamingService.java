import util.FileIO;
import util.TextUI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class StreamingService {
    private final String DEFAULT_SAVE_HEADER = "Password; History; Saved";
    private final int MINIMUM_PASSWORD_LENGTH = 6;

    private final String name;
    private final ArrayList<Media> media;
    private User user;

    public StreamingService(String name) {
        this.name = name;
        this.media = new ArrayList<>();
    }

    public void start() {
        loadMovieData("data/filmData.csv");
        loadSeriesData("data/seriesData.csv");
    }

    public void registerUser(){
        boolean running = true;
        while (running) {
            switch (TextUI.promptNumeric("Log in(1) or create user(2)?")) {
                case 1:
                    if (userLogIn(getUsername(), getPassword())) {
                        start();
                        running = false;
                    } else {
                        System.out.println("Wrong username or password");


                    }

                    break;
                case 2:
                    running = false;
                    break;
            }
        }

    }

    private String getUsername(){
        String username = TextUI.promptText("Enter your username: ");
        return username;
    }

    public String getPassword(){
        String password = TextUI.promptText("Enter your password: ");
        return password;
    }

    public boolean userLogIn(String username, String password){
        String correctPassword = FileIO.readData("data/"+username+".csv", true).getFirst();
        if (password.equals(correctPassword)){
            return true;
        }
        return false;
    }

    public void createUser(String username, String password) {
        if (password.length() < MINIMUM_PASSWORD_LENGTH) { // Check if password length is greater than 6.
            throw new IllegalArgumentException("Password length must be greater than 6");
        }
        // Pre-define save path.
        String userPath = "data/" + username + ".csv";

        // Create new user save file.
        try {
            FileIO.createFile(userPath);

            // Write password to file.
            FileIO.saveData(userPath, Arrays.asList(password), DEFAULT_SAVE_HEADER);
        } catch (IOException e) {
            System.out.println("Could not create new user!");
        }
    }

/*    public void createUser(String username, String password){
        while (password.length() <= 6){
            System.out.println("Password is too short, try again");
            password = TextUI.promptText("Enter your password: ");
        }
        ArrayList<String> data = new ArrayList<>();
        data.add(password);
        if(FileIO.createFile("data/"+username+".csv")){
            FileIO.saveData(("data/"+username+".csv"), data, "Password:");
        }
    }*/

    public void loadMovieData(String path) {
        ArrayList<String> movieData = FileIO.readData(path, false); // Read movie data.

        for (String s : movieData) {
            String[] splitData = s.split(";");

            String name = splitData[0].trim();
            String year = splitData[1].trim();
            String[] genre = splitData[2].trim().split(", ");
            try {
                double rating = Double.parseDouble(splitData[3].trim().replace(",", "."));
                // Create Movie Objects.
                this.addMedia(new Movie(name, year, genre, rating));
            } catch (NumberFormatException e) {
                System.out.println("[" + this.name + "] Could not parse movie data from file. Incorrect format!");
            }
        }
    }

    public void loadSeriesData(String path) {
        ArrayList<String> seriesData = FileIO.readData(path, false);

        for (String s : seriesData) {
            String[] splitData = s.split(";");
            String name = splitData[0].trim();
            String year = splitData[1].trim();
            String[] genres = splitData[2].trim().split(", ");

            try {
                double rating = Double.parseDouble(splitData[3].trim().replace(",", "."));
                String[] episodes = splitData[4].trim().split(", ");

                // Create series Objects.
                this.addMedia(new Series(name, year, genres, rating, episodes));
            } catch (NumberFormatException e) {
                System.out.println("[" + this.name + "] Could not parse movie data from file. Incorrect format!");
            }
        }
    }


    public boolean addMedia(Media media) {
        return this.media.add(media);
    }

    public ArrayList<Media> getMedia() {
        return this.media;
    }
}