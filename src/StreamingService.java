import util.FileIO;

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

    public void startStreamingService() {
        loadMedia();
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