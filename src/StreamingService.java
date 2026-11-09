import util.FileIO;
import util.PasswordEncryption;
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

    public boolean checkCredentials(String username, String password){
        try {
            String correctPassword = FileIO.readData("data/" + username + ".csv", true).getFirst();
            return PasswordEncryption.checkPassword(password.toCharArray(), correctPassword);
        } catch (Exception e) {
            System.out.println("Could not check credentials!");
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
            try {
                FileIO.saveData(userPath, Arrays.asList(PasswordEncryption.createStoredKey(password.toCharArray())), DEFAULT_SAVE_HEADER);
            } catch (Exception e) {
                System.out.println("Could not encrypt password!");
            }
        } catch (IOException e) {
            System.out.println("Could not create new user!");
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