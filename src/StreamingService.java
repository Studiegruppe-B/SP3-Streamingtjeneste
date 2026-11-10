import util.FileIO;
import util.PasswordEncryption;
import util.TextUI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StreamingService {
    private final String DEFAULT_SAVE_HEADER = "Password; History; Saved";
    private final int MINIMUM_PASSWORD_LENGTH = 6;
    private final int MINIMUM_SEARCH_LENGTH = 1;

    private final String name;
    private final ArrayList<Media> media;
    private final HashMap<String, Media> mediaById;
    private User user;

    public StreamingService(String name) {
        this.name = name;
        this.mediaById = new HashMap<>();
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

    public void saveUser() {
        String history = String.join("," ,user.getHistory().stream().map(Media::getId).toList());
        String saved = String.join(",", user.getSavedMedia().stream().map(Media::getId).toList());

        FileIO.saveData("data/" + user.getName() + ".csv", List.of(user.getPasswordHash() + "; " + history + "; " + saved), DEFAULT_SAVE_HEADER);
    }

    public User loadUser(String username) {
        // Read user data.
        String[] parts = FileIO.readData("data/" + username + ".csv", true).getFirst().split(";");

        // Assign passwordHash variable.
        String passwordHash = parts[0].trim();

        // Parsed 'watched' data, and add to ArrayList
        ArrayList<Media> watched = new ArrayList<>();
        if (parts.length > 1 && !parts[1].isBlank()) {
            for (String id : parts[1].trim().split(",")) {
                Media m = getMediaById(id.trim());
                if (m != null) watched.add(m);
            }
        }

        // Parsed 'saved' data, and add to ArrayList
        ArrayList<Media> saved = new ArrayList<>();
        if (parts.length > 2 && !parts[2].isBlank()) {
            for (String id : parts[2].trim().split(",")) {
                Media s = getMediaById(id.trim());
                if (s != null) saved.add(s);
            }
        }
        return new User(username, passwordHash, watched, saved);
    }

    public ArrayList<Media> getMediaByName(String search) {
        if (search.trim().length() < MINIMUM_SEARCH_LENGTH) {
            throw new IllegalArgumentException("Search must be at least " + MINIMUM_SEARCH_LENGTH + " characters");
        }

        String query = search.trim().toLowerCase();
        ArrayList<Media> results = new ArrayList<>();

        for (Media m : media) {
            if (m.getName().toLowerCase().contains(query)) {
                results.add(m);
            }
        }
        return results;
    }
    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = new ArrayList<>();

        for (Media m : media) {
            for (String g : m.getGenre()) {
                String category = g.trim();
                if (!categories.contains(category)) {
                    categories.add(category);
                }
            }
        }
        return categories;
    }


    public ArrayList<Media> getMediaByCategory(String category) {
        ArrayList<Media> results = new ArrayList<>();
        for (Media m : media) {
            for (String g : m.getGenre()) {
                if (g.trim().equalsIgnoreCase(category)) {
                    results.add(m);
                    break;
                }
            }
        }
        return results;
    }


    public void loadMovieData(String path) {
        ArrayList<String> movieData = FileIO.readData(path, false); // Read movie data.

        for (int i = 0; i < movieData.size(); i++) {
            String[] splitData = movieData.get(i).split(";");

            String name = splitData[0].trim();
            String year = splitData[1].trim();
            String[] genre = splitData[2].trim().split(", ");
            try {
                double rating = Double.parseDouble(splitData[3].trim().replace(",", "."));
                // Create Movie Objects.
                this.addMedia(new Movie("M" + i, name, year, genre, rating));
            } catch (NumberFormatException e) {
                System.out.println("[" + this.name + "] Could not parse movie data from file. Incorrect format!");
            }
        }
    }

    public void loadSeriesData(String path) {
        ArrayList<String> seriesData = FileIO.readData(path, false);

        for (int i = 0; i < seriesData.size(); i++) {
            String[] splitData = seriesData.get(i).split(";");
            String name = splitData[0].trim();
            String year = splitData[1].trim();
            String[] genres = splitData[2].trim().split(", ");

            try {
                double rating = Double.parseDouble(splitData[3].trim().replace(",", "."));
                String[] episodes = splitData[4].trim().split(", ");

                // Create series Objects.
                this.addMedia(new Series("S" + i, name, year, genres, rating, episodes));
            } catch (NumberFormatException e) {
                System.out.println("[" + this.name + "] Could not parse movie data from file. Incorrect format!");
            }
        }
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Media getMediaById(String id) {
        return mediaById.get(id);
    }

    public boolean addMedia(Media media) {
        mediaById.put(media.getId(), media);
        return this.media.add(media);
    }

    public ArrayList<Media> getMedia() {
        return this.media;
    }
}