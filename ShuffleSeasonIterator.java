import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffledEpisodes;
    private int currentIndex = 0;

    public ShuffleSeasonIterator(List<Episode> episodes) {
        shuffledEpisodes = new ArrayList<>(episodes);
        Collections.shuffle(shuffledEpisodes, new Random(42)); // fixed seed
    }

    @Override
    public boolean hasNext() {
        return currentIndex < shuffledEpisodes.size();
    }

    @Override
    public Episode next() {
        return shuffledEpisodes.get(currentIndex++);
    }
}