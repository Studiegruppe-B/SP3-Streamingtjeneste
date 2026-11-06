import java.util.ArrayList;

public class User {
    private String name;
    ArrayList<Media> watchedMedia;
    ArrayList<Media> savedMedia;

    public User(String name) {
        this.name = name;
    }

    public User(String name, ArrayList<Media> watchedMedia) {
        this.name = name;
        this.watchedMedia = watchedMedia;
    }

    public User(String name, ArrayList<Media> watchedMedia, ArrayList<Media> savedMedia) {
        this.name = name;
        this.watchedMedia = watchedMedia;
        this.savedMedia = savedMedia;
    }


}
