import java.util.ArrayList;

public class Series extends Media {
    private ArrayList<String> episodes;

    public Series(String name, ArrayList<String> genre, int year, double rating, ArrayList<String> episodes) {
        super(name, genre, year, rating);
        this.episodes = episodes;
    }

    @Override
    public void play() {
        System.out.println("Playing series: " + super.name);
    }
}