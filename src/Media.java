import java.util.ArrayList;

public abstract class Media {
    protected String name;
    protected String[] genre;
    protected String year;
    protected double rating;

    public Media(String name, String year, String[] genre, double rating) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }
}