import java.util.ArrayList;

public class Series extends Media {
    private ArrayList<Episode> episodes;

    public Series(String name, ArrayList<String> genre, int year, double rating, ArrayList<String> episodes) {
        super(name, genre, year, rating);

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