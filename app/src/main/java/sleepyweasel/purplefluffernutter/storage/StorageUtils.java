package sleepyweasel.purplefluffernutter.storage;

public class StorageUtils {
    public static MovieEntryStorage getMovieStorage() {
        return new MovieEntryStorageORM();
    }
}
