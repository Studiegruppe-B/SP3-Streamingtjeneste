import java.util.ArrayList;

public class Series extends Media {
    private final ArrayList<Episode> episodes;

    public Series(String name, String year, String[] genre, double rating, String[] episodes) {
        super(name, year, genre, rating);

        this.episodes = new ArrayList<>();

        for (String s : episodes) { // create episodes from constructor array.
            this.addEpisode(new Episode(this, s));
        }
    }

    private boolean addEpisode(Episode episode) {
        return this.episodes.add(episode);
    }

    public ArrayList<Episode> getEpisodes() {
        return this.episodes;
    }
}