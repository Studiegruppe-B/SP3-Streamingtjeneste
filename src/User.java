import java.util.ArrayList;

public class User {
    private String name;
    private String passwordHash;
    ArrayList<Media> history;
    ArrayList<Media> savedMedia;

    public User(String name, String passwordHash, ArrayList<Media> history, ArrayList<Media> savedMedia) {
        this.passwordHash = passwordHash;
        this.name = name;
        this.history = history;
        this.savedMedia = savedMedia;
    }
    // tilføjer media til watchedmedia
    public void addHistory(Media media) {
        if (media != null) {
            history.add(media);
        }
    }

    // Tilføjer media til watched media
    public void addSaved(Media media) {
        if (media != null) {
            savedMedia.add(media);
        }
    }
    // getter
    public ArrayList<Media> getHistory() {
        return history;
    }

    //getter
    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }

    public boolean hasSaved(Media media) {
        return this.savedMedia.contains(media);
    }

    // sletter users watchedHistory Array
    public void clearHistory() {
        history.clear();
        }
    public String getPasswordHash() {
        return this.passwordHash;
    }

    //fjerner en media fra watchedHistory Array
    public void deleteFromHistory(Media media){
        history.remove(media);
    }

    //Fjerner en media fra savedMedia
    public void deleteFromSaved(Media media){
        savedMedia.remove(media);
    }
    public String getName() {
        return this.name;
    }


}
