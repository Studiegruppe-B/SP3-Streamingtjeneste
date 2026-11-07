import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {

    @BeforeEach
    void beforeEach() {
        StreamingService streamingService = new StreamingService("Test");

        streamingService.loadSeriesData("data/seriesData.csv");

        ArrayList<Media> media = streamingService.getMedia();

        ArrayList<Series> series = new ArrayList<>();

        for (Media m : media) {
            if (m instanceof Series) {
                series.add((Series) m);
            }
        }

        System.out.println("[Series Test] Total series: " + series.size());
    }

    @Test
    void getEpisodes() {
    }
}