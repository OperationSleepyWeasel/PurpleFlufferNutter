package sleepyweasel.purplefluffernutter.storage;

import java.util.HashMap;
import java.util.Map;

import sleepyweasel.purplefluffernutter.MovieEntry;

public class LightStorage {

    private HashMap<Integer,MovieEntry> movies;

    private int nextId;

    private LightStorage() {
        movies = new HashMap<>();
        nextId = 0;
    }

    public static LightStorage getInstance() {
        return LightStorageHolder.instance;
    }

    public int size() {
        return movies.size();
    }

    public void clear() {
        movies.clear();
        nextId = 0;
    }

    public Iterable<? extends Map.Entry<Integer, MovieEntry>> entrySet() {
        return movies.entrySet();
    }

    public java.util.Collection<MovieEntry> values() {
        return movies.values();
    }

    public void add(MovieEntry entry) {
        movies.put(nextId, entry);
        ++nextId;
    }

    public MovieEntry get(int id) {
        return movies.get(id);
    }

    private static class LightStorageHolder {
        private final static LightStorage instance = new LightStorage();
    }
}
