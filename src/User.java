import java.util.ArrayList;

public class User {
    private String name;
    private String passwordHash;
    ArrayList<Media> watchedMedia;
    ArrayList<Media> savedMedia;

    public User(String name, String passwordHash, ArrayList<Media> watchedMedia, ArrayList<Media> savedMedia) {
        this.passwordHash = passwordHash;
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

    // sletter users watchedHistory Array
    public void clearHistory() {
        watchedMedia.clear();
        }
    public String getPasswordHash() {
        return this.passwordHash;
    }

    //fjerner en media fra watchedHistory Array
    public void deleteFromHistory(Media media){
        watchedMedia.remove(media);
    }

    //Fjerner en media fra savedMedia
    public void deleteFromSaved(Media media){
        savedMedia.remove(media);
    }
    public String getName() {
        return this.name;
    }




}
