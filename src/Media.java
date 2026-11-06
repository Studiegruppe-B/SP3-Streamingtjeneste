import java.util.ArrayList;

public abstract class Media {
    protected String name;
    protected ArrayList<String> genre;
    protected int year;
    protected double rating;

    public Media(String name, ArrayList<String> genre, int year, double rating) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public abstract void play();
}
