import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    StreamingService s;
    User u;
    @BeforeEach
    void BeforeEach(){
        s = new StreamingService("TestService");
        s.loadMovieData("data/filmData.csv");
        s.setUser(s.loadUser("TestUser"));
        u = s.getUser();
    }

    @Test
    void addHistory() {
        u.addHistory(s.getMediaById("M0"));

        assertEquals("The Godfather", u.getHistory().getFirst().name);
    }

    @Test
    void deleteFromHistory(){
        // u.deleteFromHistory(m);
    }

    @Test
    void deleteFromSaved(){
        // u.deleteFromSaved(new Movie("name", "2020", new String[] {"Romance"}, 8.9 ));
    }

    @Test
    void clearHistory(){
        u.clearHistory();
    }

}