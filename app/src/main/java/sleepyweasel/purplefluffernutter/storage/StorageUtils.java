package sleepyweasel.purplefluffernutter.storage;

import sleepyweasel.purplefluffernutter.MovieEntryStorage;
import sleepyweasel.purplefluffernutter.MovieEntryStorageVolatile;

public class StorageUtils {
    public static MovieEntryStorage getMovieStorage() {
        return new MovieEntryStorageVolatile();
    }
}
