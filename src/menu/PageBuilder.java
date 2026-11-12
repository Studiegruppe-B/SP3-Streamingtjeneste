package menu;

import util.TextUI;

import java.util.ArrayDeque;
import java.util.Deque;

public class PageBuilder {
    private final Deque<PageTemplate> history = new ArrayDeque<>();
    private PageTemplate current;

    private boolean running = true;

    //logik metoder
    public void goTo(PageTemplate next) {
        history.push(current);
        current = next;
    }
    public void back(){
        current.clearOptions();
        current = history.pop();
    }

    public void destroy() {
        current.clearOptions();
        history.removeLast();
    }

    public void exit() {
        running = false;
    }
    public boolean isRunning() {
        return running;
    }
    public PageTemplate getCurrent() {
        return current;
    }

    public void select(PageTemplate current) {
        this.current = current;
    }

    public void display() {
        if (this.getCurrent() == null) {
            throw new NullPointerException("[PageBuilder] Current page has not been set!");
        }
        while (this.isRunning()) {
            PageTemplate current = this.getCurrent();
            int choice = TextUI.promptMenuChoice(current.getOptions(), this.getCurrent().getTitle());

            if (choice >= 1 && choice <= current.getOptions().size()) {
                current.choice(choice - 1);
            } else {
                TextUI.userMessage("Invalid choice");
            }
        }
    }




    //konfigurer for  bruger valg
   /* public static void buildPages(){

        //konfigurer for final bruger valg
        startPage.addSingleOptions(new String[]{"Exit", "Log in","Sign in"},
                ()->exit()      //Exit knap
               // ()->logIn(),    //LoginKnap: skal bruge goTo(homePage); hvis login godkendt
              //  ()->signIn(),   //SigninKnap: skal bruge goTo(homePage);
        );
        homePage.addSingleOptions(new String[]{"Exit", "Search", "Filter"},
                ()->exit(),
              //  ()->search(),  //Søg efter film: skal bruge goTo(searchedPage);
                ()-> goTo(filterPage)
        );
        filterPage.addSingleOptions(new String[]{"Back", "Genre", "Watched", "Saved"},
                ()->back(),
                ()->goTo(genrePage),
                ()->goTo(watchedPage),
                ()->goTo(savedPage)
        );
        searchedPage.addSingleOptions(new String[]{"Back"},   //Liste af search resultater
                ()->back()
        );
        genrePage.addSingleOptions(new String[]{"Back"},  //Liste af alle genre
                ()->back()
        );
        watchedPage.addSingleOptions(new String[]{"Back","Clear History"},   //Liste af watched media
                ()->back(),
                ()->watchedPage.clearOptions(2,watchedPage.getOptions().size())

        );
        savedPage.addSingleOptions(new String[]{"Back"},   //Liste af saved media
                ()->back()
        );

        seasonPage.addSingleOptions(new String[]{"Back", "Save"},          //Liste list af episoder
                ()->back()
               // ()->save()

        );
        removableSeasonPage.addSingleOptions(new String[]{"Back","Save","Remove"},  //Liste af episoder
                ()->back()
              //  ()->save(),
              //  ()-> remove()
        );
        PlayPage.addSingleOptions(new String[]{"Back","Play","Save"},
                ()->back()
               // ()->play(),
               // ()->save()

        );
        removablePlayPage.addSingleOptions(new String[]{"Back","Play","Save","Remove"},
                ()->back()
              ()->play(),
                ()->save(),
                ()->remove()
        );
*/
        //Konfigurer ArrayList options.










    }




