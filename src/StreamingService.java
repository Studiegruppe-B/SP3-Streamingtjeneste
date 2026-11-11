import menu.PageBuilder;
import menu.PageTemplate;
import util.FileIO;
import util.PasswordEncryption;
import util.TextUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StreamingService {
    private final String DEFAULT_SAVE_HEADER = "Password; History; Saved";
    private final int MINIMUM_PASSWORD_LENGTH = 6;
    private final int MINIMUM_SEARCH_LENGTH = 1;

    private final String name;
    private final ArrayList<Media> media = new ArrayList<>();
    private final HashMap<String, Media> mediaById = new HashMap<>();
    private User user;

    // Menu page templates
    private PageBuilder pageBuilder = new PageBuilder();

    //Instances of pages:
    private final PageTemplate startPage = new PageTemplate("Chill - StreamingService");
    private final PageTemplate homePage = new PageTemplate("Homepage");
/*    private final PageTemplate searchPage = new PageTemplate();
    private final PageTemplate filterPage = new PageTemplate();
    private final PageTemplate genrePage = new PageTemplate();
    private final PageTemplate watchedPage = new PageTemplate();
    private final PageTemplate savedPage = new PageTemplate();
    private final PageTemplate mediaPage = new PageTemplate();
    private final PageTemplate inWatchedPage = new PageTemplate();
    private final PageTemplate inGenrePage = new PageTemplate();
    private final PageTemplate inSavedPage = new PageTemplate();
    private final PageTemplate seasonPage = new PageTemplate();
    private final PageTemplate savedSeasonPage = new PageTemplate();
    private final PageTemplate normalPlayablePage = new PageTemplate();
    private final PageTemplate SavedPlayablePage = new PageTemplate();*/

    public StreamingService(String name) {
        this.name = name;
    }

    public void start() {
        loadMovieData("data/filmData.csv");
        loadSeriesData("data/seriesData.csv");
        buildMenu();

        // Display built menu.
        pageBuilder.display();
    }

    private void buildMenu() {
        startPage.addSingleOptions(new String[]{"Exit", "Goto homepage", "Log in", "Sign up"},
                pageBuilder::exit, //Exit knap
                () -> pageBuilder.goTo(homePage),

                // Login prompt:
                () -> {
                    String username = TextUI.promptText("Please enter your username");
                    String password = TextUI.promptText("Please enter your password");

                    // Check if input credentials are OK
                    if (checkCredentials(username, password)) {
                        setUser(loadUser(username));
                        homePage.setTitle(homePage.getTitle() + " (User: " + this.getUser().getName() + ")");
                        pageBuilder.goTo(homePage);
                    }
                },

                // Sign up prompt:
                () -> {
                    String username = TextUI.promptText("Please enter your username");
                    String password = TextUI.promptText("Please enter your password");

                    try {
                        createUser(username, password);
                    } catch (IllegalArgumentException e) {
                        TextUI.userMessage(e.getMessage());
                    }
                }
        );

        homePage.addSingleOptions(new String[]{"Exit", "Search"},
                pageBuilder::exit,
                //  ()->search(),  //Søg efter film: skal bruge goTo(searchedPage);
                ()-> {
                    String query = TextUI.promptText("Please enter the whole or a part of a title: ");

                    ArrayList<Media> titles = getMediaByName(query);

                    int picked = TextUI.promptMenuChoice(titles.stream().map(Media::getName).toList(), "Search result:");
                }
        );



        pageBuilder.select(startPage);
    }

    public boolean checkCredentials(String username, String password){
        try {
            String correctPassword = FileIO.readData("data/" + username + ".csv", true).getFirst();
            return PasswordEncryption.checkPassword(password.toCharArray(), correctPassword);
        } catch (Exception e) {
            System.out.println("User not found!");
        }
        return false;
    }

    public void createUser(String username, String password) throws IllegalArgumentException {
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
        if (search == null || search.trim().length() < MINIMUM_SEARCH_LENGTH) {
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