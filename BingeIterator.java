import java.util.List;

public class BingeIterator implements EpisodeIterator {
    private final List<Season> seasons;
    private int currentSeasonIndex = 0;
    private EpisodeIterator currentIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasons = seasons;
        if (!seasons.isEmpty()) {
            currentIterator = seasons.get(0).createNormalIterator();
        }
    }

    @Override
    public boolean hasNext() {
        while (currentIterator != null) {
            if (currentIterator.hasNext()) {
                return true;
            } else if (++currentSeasonIndex < seasons.size()) {
                currentIterator = seasons.get(currentSeasonIndex).createNormalIterator();
            } else {
                currentIterator = null;
            }
        }
        return false;
    }

    @Override
    public Episode next() {
        return currentIterator.next();
    }
}