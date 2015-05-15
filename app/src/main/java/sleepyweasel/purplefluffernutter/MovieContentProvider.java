package sleepyweasel.purplefluffernutter;

import java.util.HashMap;
import java.util.Map;

public class MovieContentProvider {

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

    public MovieEntry getEntryByTitle(String movieTitle) {
        for (Map.Entry<Integer,MovieEntry> entry : movieList.entrySet()) {
            MovieEntry movie = entry.getValue();
            if (movie.getTitle().equals(movieTitle))
                return movie;
        }
        return null;
    }
}
