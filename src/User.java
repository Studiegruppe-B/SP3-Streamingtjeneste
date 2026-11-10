import java.util.ArrayList;

public class User {
    private String name;
    private String password;
    ArrayList<Media> watchedMedia;
    ArrayList<Media> savedMedia;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.watchedMedia = new ArrayList<>();
        this.savedMedia = new ArrayList<>();
    }


    // tilføjer media til watchedmedia
    public void addHistory(Media media) {
        if (media != null) {
            watchedMedia.add(media);
        }
    }

    // tilføjer media til watched media
    public void addSaved(Media media) {
        if (media != null) {
            savedMedia.add(media);
        }
    }
    // getter
    public ArrayList<Media> getWatched() {
        return watchedMedia;
    }

    //getter
    public ArrayList<Media> getSaved() {
        return savedMedia;
    }

    // sletter users watchedHistory Array
    public void clearHistory() {
        watchedMedia.clear();
    }

    //fjerner en media fra watchedHistory Array
    public void deleteFromHistory(Media media){
        watchedMedia.remove(media);
    }

    //Fjerner en media fra savedMedia
    public void deleteFromSaved(Media media){
        savedMedia.remove(media);
    }




}
