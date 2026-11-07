public class Episode implements Playable {
    private final String episode;

    private final Series parent;

    public Episode(Series parent, String episode) {
        this.parent = parent;
        this.episode = episode;
    }

    public String getEpisode() {
        return episode;
    }

    @Override
    public void play() {
        System.out.println("Now playing: " + parent.name + " (Episode: " + this.episode + ")");
    }
}
