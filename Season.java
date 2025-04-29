import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public EpisodeIterator createNormalIterator() {
        return new SeasonIterator(episodes);
    }

    public EpisodeIterator createReverseIterator() {
        return new ReverseSeasonIterator(episodes);
    }

    public EpisodeIterator createShuffleIterator() {
        return new ShuffleSeasonIterator(episodes);
    }

    @Override
    public Iterator<Episode> iterator() {
        return episodes.iterator();
    }
}