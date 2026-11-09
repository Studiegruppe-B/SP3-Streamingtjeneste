import util.FileIO;
import util.TextUI;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamingService {
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
        switch (TextUI.promptNumeric("Log in(1) or create user(2)?")){
            case 1:
                userLogIn();
                break;
            case 2:
                createUser();
                break;
        }

    }

    public void userLogIn(){
        String username = TextUI.promptText("Enter your username: ");
        String password = TextUI.promptText("Enter your password: ");
        String correctPassword = FileIO.readData("data/"+username+".csv", true).get(0);
        if (password.equals(correctPassword)){
            this.start();
        }
    }

    public void createUser(){
        String username = TextUI.promptText("Enter your username: ");
        String password = TextUI.promptText("Enter your password: ");
        while (password.length() <= 6){
            System.out.println("Password is too short, try again");
            password = TextUI.promptText("Enter your password: ");
        }
        ArrayList<String> data = new ArrayList<>();
        data.add(password);
        if(FileIO.createFile("data/"+username+".csv")){
            FileIO.saveData(("data/"+username+".csv"), data, "Password:");
            userLogIn();
        }
    }

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