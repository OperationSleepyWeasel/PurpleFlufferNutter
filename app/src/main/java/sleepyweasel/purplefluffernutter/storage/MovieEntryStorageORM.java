package sleepyweasel.purplefluffernutter.storage;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;

import sleepyweasel.purplefluffernutter.MovieEntry;

public class MovieEntryStorageORM implements MovieEntryStorage {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void addMovie(MovieEntry entry) {
        entry.save();
    }

    @Override
    public int size() {
        return (int) MovieEntry.count(MovieEntry.class, null, null);
    }

    @Override
    public MovieEntry getEntry(int id) {
        MovieEntry entry = MovieEntry.findById(MovieEntry.class, (long) id);
        return entry;
    }

    @Override
    public MovieEntry getEntryByTitle(String movieTitle) {
        Select movieByTitleSelect = Select.from(MovieEntry.class).where(Condition.prop("title").eq(movieTitle));
        return (MovieEntry) movieByTitleSelect.first();
    }

    @Override
    public void clear() {
        MovieEntry.deleteAll(MovieEntry.class);
    }

    @Override
    public ArrayList<String> getMovieTitles() {
        return null;
    }
}
