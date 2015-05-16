package sleepyweasel.purplefluffernutter.storage;

import sleepyweasel.purplefluffernutter.MovieEntryStorage;

public class StorageUtils {
    public static MovieEntryStorage getMovieStorage() {
        return new MovieContentProvider();
    }
}
