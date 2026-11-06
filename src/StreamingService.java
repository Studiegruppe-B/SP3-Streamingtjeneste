import util.FileIO;
import util.TextUI;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamingService {
    String name;
    ArrayList<Movie> movies;
    ArrayList<Series> series;

    public StreamingService(String name) {
        this.name = name;
        this.movies = new ArrayList<>();
        this.series = new ArrayList<>();
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


    private void loadMedia() {

        for (String line : FileIO.readData("data/filmData.csv")) {
            String[] v = line.split(";");
            String name = v[0].trim();
            int year = Integer.parseInt(v[1].trim());
            ArrayList<String> genre = new ArrayList<>(Arrays.asList(v[2].trim().split(", ")));
            double rating = Double.parseDouble(v[3].trim().replace(",", "."));

            movies.add(new Movie(name, genre, year, rating));
        }


        for (String line : FileIO.readData("data/seriesData.csv")) {
            String[] v = line.split(";");
            String name = v[0].trim();
            int year = Integer.parseInt(v[1].trim().split("-")[0]);
            ArrayList<String> genre = new ArrayList<>(Arrays.asList(v[2].trim().split(", ")));
            double rating = Double.parseDouble(v[3].trim().replace(",", "."));
            ArrayList<String> episodes = new ArrayList<>(Arrays.asList(v[4].trim().split(", ")));

            series.add(new Series(name, genre, year, rating, episodes));
        }
    }
}