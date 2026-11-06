import util.FileIO;
import util.TextUI;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamingService {
    String name;
    ArrayList<Media> media;

    public StreamingService(String name) {
        this.name = name;
        this.media = new ArrayList<>();
    }

    public void start() {
        loadMedia();
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
        String correctPassword = FileIO.readData("data/"+username+".csv").get(0);
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
            FileIO.saveData(("data/"+username+".csv"), "Password:", data);
            userLogIn();
        }
    }

    private void loadMovieData(String path) {
        ArrayList<String> movieData = FileIO.readData(path); // Read movie data.

        for (String s : movieData) {
            String[] splitData = s.split(";");

            String name = splitData[0].trim();
            String year = splitData[1].trim();
            String[] genre = splitData[2].trim().split(", ");
            try {
                double rating = Double.parseDouble(splitData[3].trim());
                // Create Movie Objects.
                this.addMedia(new Movie(name, year, genre, rating));
            } catch (NumberFormatException e) {
                System.out.println("[" + this.name + "] Could not parse movie data from file. Incorrect format!");
            }
        }
    }

    private void loadSeriesData(String path) {
        ArrayList<String> seriesData = FileIO.readData(path);

        for (String s : seriesData) {
            String[] splitData = s.split(";");
            String name = splitData[0].trim();
            String year = splitData[1].trim();
            String[] genres = splitData[2].trim().split(", ");

            try {
                double rating = Double.parseDouble(splitData[3].trim());
                String[] episodes = splitData[4].trim().split(", ");

                // Create series Objects.
                this.addMedia(new Series(name, year, genres, rating, episodes));
            } catch (NumberFormatException e) {
                System.out.println("[" + this.name + "] Could not parse movie data from file. Incorrect format!");
            }
        }
    }

    private void loadMedia() {
        ArrayList<String> movieData = FileIO.readData("data/filmData.csv"); // Read movie data.
        ArrayList<String> seriesData = FileIO.readData("data/seriesData.csv"); // Read series data.

        // Iterate through seriesData.csv, and add to our Media ArrayList
/*        for (String line : FileIO.readData("data/seriesData.csv")) {
            String[] v = line.split(";");
            String name = v[0].trim();
            int year = Integer.parseInt(v[1].trim().split("-")[0]);
            ArrayList<String> genre = new ArrayList<>(Arrays.asList(v[2].trim().split(", ")));
            double rating = Double.parseDouble(v[3].trim().replace(",", "."));
            ArrayList<String> episodes = new ArrayList<>(Arrays.asList(v[4].trim().split(", ")));

            media.add(new Series(name, genre, year, rating, episodes));
        }*/
    }

    public boolean addMedia(Media media) {
        return this.media.add(media);
    }
}