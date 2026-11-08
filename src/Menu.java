import java.util.ArrayList;
import java.util.List;

public enum Menu {
    LOGIN_SCREEN("Exit", "Sign in", "Log in"),
    HOME_SCREEN("Exit", "Search", "Filter"),
    FILTER_SCREEN("Back", "Genre", "Rating", "Watch history", "Saved for later"),
    GENRE_SCREEN("Back", "ACTION", "ADVENTURE", "BIOGRAPHY", "COMEDY", "CRIME",
            "DRAMA", "FAMILY", "FANTASY", "FILM_NOIR", "HISTORY",
            "HORROR", "MUSIC", "MUSICAL", "MYSTERY", "ROMANCE",
            "SCI_FI", "SPORT", "THRILLER", "WAR", "WESTERN"),

    // Faste valg (list skærme): medierne starter efter disse valg
    GENRE_RESULT_SCREEN("Back"),
    WATCH_HISTORY_SCREEN("Back"),
    SEARCH_RESULT_SCREEN("Back"),
    SAVED_SCREEN("Back"),

    // Valgt medie
    MOVIE_PLAY("Back", "Play", "Save"),
    SAVED_PLAY("Back", "Play", "Save", "Remove"),
    SERIES_RESULT_SCREEN("Back", "Save Show"),             // episoderne starter efter disse valg
    SAVED_SERIES_RESULT_SCREEN("Back", "Save Show", "Remove Show"),
    EPISODE_SCREEN("Back", "Play"),

    EXIT_APP();


    //Laver enum elementers id, om til en arraylist.
    public final ArrayList<String> menuOptions;
    Menu(String... menuOptions) {
        this.menuOptions = new ArrayList<>(List.of(menuOptions));
    }
}
