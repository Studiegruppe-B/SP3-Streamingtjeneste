import util.TextUI;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;

public class DisplayMenu {

    //Setter LOGIN_SCREEN til current. Current kan skifte værdi senere.
    private Menu current = Menu.LOGIN_SCREEN;

    //En deque bruges til at lave en STACK, med LIFO princip (last in, first out). Dermed kan vi få en back knap
    private final Deque<Menu> history = new ArrayDeque<>();

    // Skærme hvor et tal efter de faste valg betyder et medie eller en episode
    private final Set<Menu> listScreens = EnumSet.of(
            Menu.GENRE_RESULT_SCREEN, Menu.WATCH_HISTORY_SCREEN, Menu.SEARCH_RESULT_SCREEN,
            Menu.SAVED_SCREEN, Menu.SERIES_RESULT_SCREEN, Menu.SAVED_SERIES_RESULT_SCREEN);

    public void showMenu() {
        while (current != Menu.EXIT_APP) {
            int choice = TextUI.promptMenuChoice(current.menuOptions, "Choose:"); //bruger valg og display
            int size = current.menuOptions.size();

            if (choice >= 1 && choice <= size) {
                choiceHandler(current.menuOptions.get(choice - 1));
            } else if (choice > size && listScreens.contains(current)) {   //&& choice <= size + listSize()
                choiceHandler(String.valueOf(choice - size));               // medie- eller episodenummer
            } else {
                TextUI.userMessage("Invalid choice");
            }
        }
    }

    //Dette er state maskinen, som med Deque virker som en stack med en back button.
    //Dermed er det altså kun showMenu som skal ændres, hvis der skal være grafik. Især metoden fra TextUI.
    private void choiceHandler(String choice) {

        //pop() fjerner det øverste element i stacken.
        //Altså det sidste element som tilføjet. goTO (se nederste metode), tilføjer current, når der gåes til next.
        if (choice.equals("Back")) {
            current = history.pop();
            return;
        }
        if (choice.equals("Exit")) {
            current = Menu.EXIT_APP;
            return;
        }

        //Holder via current styr på hvilken skærm brugeren er på
        switch (current) {
            case LOGIN_SCREEN  -> loginMenu(choice);
            case HOME_SCREEN   -> homeMenu(choice);
            case FILTER_SCREEN -> filterMenu(choice);
            case GENRE_SCREEN  -> genreMenu(choice);

            case GENRE_RESULT_SCREEN, WATCH_HISTORY_SCREEN, SEARCH_RESULT_SCREEN
                    -> openMedia(choice, Menu.MOVIE_PLAY, Menu.SAVED_SERIES_RESULT_SCREEN);
            case SAVED_SCREEN
                    -> openMedia(choice, Menu.SAVED_PLAY, Menu.SAVED_SERIES_RESULT_SCREEN);

            case SERIES_RESULT_SCREEN, SAVED_SERIES_RESULT_SCREEN -> seriesMenu(choice);
            case MOVIE_PLAY, SAVED_PLAY, EPISODE_SCREEN            -> playMenu(choice);
        }
    }

    private void loginMenu(String choice) {
        if (choice.equals("Log in")) {
            //login()
        }
        if (choice.equals("Sign in")) {
            //signIn()
        }
        goTo(Menu.HOME_SCREEN);
    }

    private void homeMenu(String choice) {
        if (choice.equals("Search")) {
            //search()
            goTo(Menu.SEARCH_RESULT_SCREEN);
        }
        if (choice.equals("Filter")) {
            goTo(Menu.FILTER_SCREEN);
        }
    }

    private void filterMenu(String choice) {
        if (choice.equals("Genre")) {
            goTo(Menu.GENRE_SCREEN);
        }
        if (choice.equals("Rating")) {
            //sortByRating()
        }
        if (choice.equals("Watch history")) {
            //ArrayList for watch history
            goTo(Menu.WATCH_HISTORY_SCREEN);
        }
        if (choice.equals("Saved for later")) {
            //ArrayList med alle saved
            goTo(Menu.SAVED_SCREEN);
        }
    }

    private void genreMenu(String choice) {
        //filterByGenre(choice)
        goTo(Menu.GENRE_RESULT_SCREEN);
    }

    // Fælles for alle medielister. Interfacet afgør, hvilken skærm der åbnes.
    private void openMedia(String choice, Menu movieScreen, Menu seriesScreen) {
        //select(choice)
        boolean isMovie = true; //isMovie()
        if (isMovie) {
            goTo(movieScreen);
        } else {
            goTo(seriesScreen);
        }
    }

    // Fælles for gemte og ikke-gemte serier
    private void seriesMenu(String choice) {
        //ArrayList med alle episoder i serien
        if (choice.equals("Save Show")) {
            //save()
        } else if (choice.equals("Remove Show")) {
            //remove()
        } else {
            //select(choice)
            goTo(Menu.EPISODE_SCREEN);
        }
    }

    // Fælles for film, gemte film og episoder (ingen goTo)
    private void playMenu(String choice) {
        if (choice.equals("Play")) {
            //play()
        }
        if (choice.equals("Save")) {
            //save()
        }
        if (choice.equals("Remove")) {
            //remove()
        }
    }

    //push() lægger current øverst på stacken, og bliver derefter til next, som vi bestemmer.
    private void goTo(Menu next) {
        history.push(current);
        current = next;
    }
}