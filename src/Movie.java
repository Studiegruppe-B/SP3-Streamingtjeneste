import util.TextUI;

import java.util.ArrayList;

public class Movie extends Media implements Playable {
    public Movie(String name, ArrayList<String> genre, int year, double rating) {
        super(name, genre, year, rating);
    }

    @Override
    public void play() {
        TextUI.userMessage("Playing movie: " + super.name);
    }
}