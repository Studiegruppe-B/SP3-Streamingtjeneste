import java.util.ArrayList;

public abstract class Media {
    private String id;
    protected String name;
    protected String[] genre;
    protected String year;
    protected double rating;

    public Media(String id, String name, String year, String[] genre, double rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public String getId() {
        return this.id;
    }
}