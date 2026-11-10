package menu;

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
    public PageTemplate back(){
        return current = history.pop();
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


    //Instances of pages:
    static PageTemplate startPage = new PageTemplate();
    static PageTemplate homePage = new PageTemplate();
    static PageTemplate searchPage = new PageTemplate();
    static PageTemplate filterPage = new PageTemplate();
    static PageTemplate genrePage = new PageTemplate();
    static PageTemplate watchedPage = new PageTemplate();
    static PageTemplate savedPage = new PageTemplate();
    static PageTemplate mediaPage = new PageTemplate();
    PageTemplate inWatchedPage = new PageTemplate();
    PageTemplate inGenrePage = new PageTemplate();
    PageTemplate inSavedPage = new PageTemplate();
    static PageTemplate seasonPage = new PageTemplate();
    static PageTemplate savedSeasonPage = new PageTemplate();
    static PageTemplate normalPlayablePage = new PageTemplate();
    static PageTemplate SavedPlayablePage = new PageTemplate();


    //konfigurer for  bruger valg
    public static void buildPages(){

        //konfigurer for final bruger valg
        startPage.addSingleOptions(new String[]{"Exit", "Log in","Sign in"},
                ()->{return;},      //Exit knap
                ()->{/*login() AND goTo()*/},    //LoginKnap
                ()->{/*signin()*/}
        );
        homePage.addSingleOptions(new String[]{"Exit", "Search", "Filter"},
                ()->{return;},
                ()->{/*search() AND goTo()*/ },
                ()->{/* goTO()*/}
        );
        searchPage.addSingleOptions(new String[]{"Back"},
                ()->{/*back()*/}
        );
        filterPage.addSingleOptions(new String[]{"Back", "Genre", "Watched", "Saved"},
                ()->{/*back()*/}
                );
        genrePage.addSingleOptions(new String[]{"Back"},
                ()->{/*back()*/}
        );
        watchedPage.addSingleOptions(new String[]{"Back"},
                ()->{/*back()*/}
        );
        savedPage.addSingleOptions(new String[]{"Back"},
                ()->{/*back()*/}
        );
        mediaPage.addSingleOptions(new String[]{"Back"},
                ()->{/*back()*/}
        );

        seasonPage.addSingleOptions(new String[]{"Back", "Save"},
                ()->{/*back()*/}
        );
        savedSeasonPage.addSingleOptions(new String[]{"Back","Save","Remove"},
                ()->{/*back()*/}
        );
        normalPlayablePage.addSingleOptions(new String[]{"Back","Play","Save"},
                ()->{/*back()*/}
        );
        SavedPlayablePage.addSingleOptions(new String[]{"Back","Play","Save","Remove"},
                ()->{/*back()*/}
        );




    }



}
