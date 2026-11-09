import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    StreamingService s;
    ArrayList<Media> m;
    ArrayList<Movie> mo;

    @BeforeEach
    void beforeEach() {
        s = new StreamingService("Test");

        s.loadMovieData("data/filmData.csv");

        m = s.getMedia();

        mo = new ArrayList<>();

        for (Media m : m) {
            if (m instanceof Movie) {
                mo.add((Movie) m);
            }
        }

        System.out.println("[Movies Test] Total moves: " + mo.size());
    }

    @Test
    void moviesShouldNotBeZero() {
        assertNotEquals(0, mo.size());

    }

}