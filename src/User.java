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
    // tilføjer media til watchedmedia
    public void addWatchedMedia(Media media) {
        if (media != null) {
            watchedMedia.add(media);
        }
    }

    // tilføjer media til watched media
    public void addSavedMedia(Media media) {
        if (media != null) {
            savedMedia.add(media);
        }
    }
    // getter
    public ArrayList<Media> getWatchedMedia() {
        return watchedMedia;
    }

    //getter
    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }


}
