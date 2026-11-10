import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StreamingServiceTest {
    StreamingService s;

    @BeforeEach
    void beforeEach() {
        s = new StreamingService("Test Service");
    }
    @Test
    void getMediaByCategory() {
        s.loadMovieData("data/filmData.csv");
        s.loadSeriesData("data/seriesData.csv");

        ArrayList<Media> results = s.getMediaByCategory("Crime");
        assertFalse(results.isEmpty());
    }
    @Test
    void getAllCategories() {
        s.loadMovieData("data/filmData.csv");
        s.loadSeriesData("data/seriesData.csv");

        ArrayList<String> categories = s.getAllCategories();
        System.out.println(categories);
    }
    @Test
    void getMediaByName() {
        s.loadMovieData("data/filmData.csv");
        s.loadSeriesData("data/seriesData.csv");

        ArrayList<Media> results = s.getMediaByName("god");
        for (Media m : results) {
            System.out.println(m.getName());
        }
    }


    @Test
    void registerUser() {
        s.createUser("TestUser", "TestPassword");
    }

    @Test
    void checkCredentials() {
        assertTrue(s.checkCredentials("TestUser", "TestPassword"));

    }

    @Test
    void saveUser() {
        s.loadMovieData("data/filmData.csv");
        s.loadSeriesData("data/seriesData.csv");
        s.setUser(new User("TestUser2", "dinmor", s.getMedia(), s.getMedia()));
        s.saveUser();
    }

    @Test
    void loadUser() {
        s.loadMovieData("data/filmData.csv");
        s.loadSeriesData("data/seriesData.csv");
        s.setUser(s.loadUser("TestUser"));
        System.out.println(s.getUser().getPasswordHash());

        for (Media m : s.getUser().getSavedMedia()) {
            System.out.println(m.name);
        }
    }
}