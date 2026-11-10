import util.TextUI;

import java.util.ArrayList;

public class Movie extends Media implements Playable {
    public Movie(String id, String name, String year, String[] genre, double rating) {
        super(id, name, year, genre, rating);
    }

    @Override
    public void play() {
        TextUI.userMessage("Playing movie: " + super.name);
    }
}