package menu;

public class PageBuilder {

    //Instances of pages:
    PageTemplate startPage = new PageTemplate(MenuEnum.START_PAGE);
    PageTemplate homePage = new PageTemplate(MenuEnum.HOME_PAGE);
    PageTemplate searchPage = new PageTemplate(MenuEnum.SEARCH_PAGE);
    PageTemplate filterPage = new PageTemplate(MenuEnum.FILTER_PAGE );
    PageTemplate genrePage = new PageTemplate(MenuEnum.GENRE_PAGE);
    PageTemplate watchedPage = new PageTemplate(MenuEnum.WATCHED_PAGE);
    PageTemplate savedPage = new PageTemplate(MenuEnum.SAVED_PAGE);
    PageTemplate mediaPage = new PageTemplate(MenuEnum.MEDIA_PAGE);
    PageTemplate inWatchedPage = new PageTemplate(MenuEnum.IN_WATCHED_PAGE);
    PageTemplate inGenrePage = new PageTemplate(MenuEnum.IN_GENRE_PAGE);
    PageTemplate inSavedPage = new PageTemplate(MenuEnum.IN_SAVED_PAGE);
    PageTemplate seasonPage = new PageTemplate(MenuEnum.SEASON_PAGE);
    PageTemplate savedSeasonPage = new PageTemplate(MenuEnum.SAVED_SEASON_PAGE);
    PageTemplate normalPlayablePage = new PageTemplate(MenuEnum.PLAY_PAGE);
    PageTemplate getSavedPlayablePage = new PageTemplate(MenuEnum.PLAY_SAVED_PAGE);


    //Configuring the specific userOptions:

    public void buildPages(){
        startPage.addOptions(new String[]{"Exit", "Sign in", "Log in"},
                ()->{return;},      //Exit knap
                ()->{/*login()*//*goTo()*/},    //LoginKnap
                ()->{/*signin()*/}
        );

    }

}
