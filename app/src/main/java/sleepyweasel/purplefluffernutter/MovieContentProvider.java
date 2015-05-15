package sleepyweasel.purplefluffernutter;

import java.util.HashMap;

public class MovieContentProvider {

    private int size;

    private int nextId;

    private HashMap<Integer,MovieEntry> movieList;

    public MovieContentProvider() {
        movieList = new HashMap<>();
        nextId = 0;
    }

    public boolean isEmpty() {
        return movieList.isEmpty();
    }

    public void addMovie(MovieEntry entry) {
        movieList.put(nextId, entry);
        nextId++;
    }

    public int size() {
        return movieList.size();
    }

    public MovieEntry getEntry(int id) {
        return movieList.get(id);
    }
}
