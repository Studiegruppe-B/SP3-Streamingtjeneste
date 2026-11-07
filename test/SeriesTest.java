import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {

    StreamingService s;
    ArrayList<Media> m;
    ArrayList<Series> se;

    @BeforeEach
    void beforeEach() {
        s = new StreamingService("Test");

        s.loadSeriesData("data/seriesData.csv");

        m = s.getMedia();

        se = new ArrayList<>();

        for (Media m : m) {
            if (m instanceof Series) {
                se.add((Series) m);
            }
        }

        System.out.println("[Series Test] Total series: " + se.size());
    }

    @Test
    void seriesTotalShouldNotBeZero() {
        assertNotEquals(0, se.size());
    }

    @Test
    void episodesShouldNotBeZero() {
        int size = se.getFirst().getEpisodes().size();

        assertNotEquals(0, size);
    }

    @Test
    void playEpisodes() {
        for (Episode e : se.getFirst().getEpisodes()) {
            e.play();
        }
    }

}