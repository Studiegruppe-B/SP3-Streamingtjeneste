import java.util.ArrayList;

public class User {
    private String name;
    ArrayList<Media> watchedMedia;
    ArrayList<Media> savedMedia;

    public User(String name, ArrayList<Media> watchedMedia) {
        this.name = name;
        this.watchedMedia = watchedMedia;
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
    public void clearHistory(){
        for (Media media : watchedMedia){
            watchedMedia.remove(media);
        }
    }

    //fjerner en media fra watchedHistory Array
    public void deleteFromHistory(Media media){
        if (watchedMedia.remove(media)){
            System.out.println("Media was removed from watch history");
        } else {
            System.out.println("Media was not found in watch history");
        }
    }




}
