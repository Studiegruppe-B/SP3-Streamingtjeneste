public class Episode implements Playable {
    private final String episode;

    private final Series parent;

    private final String name;

    public Episode(Series parent, String episode) {
        this.parent = parent;
        this.episode = episode;
        this.name = parent.name + "(" + this.episode + ")";
    }

    public String getEpisode() {
        return episode;
    }

    @Override
    public void play() {
        System.out.println("Now playing: " + parent.name + " (Episode: " + this.episode + ")");
    }

    public String getName() {
        return this.name;
    }

    public Series getSeries() {
        return this.parent;
    }
}
