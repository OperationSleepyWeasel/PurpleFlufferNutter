package sleepyweasel.purplefluffernutter;

import java.util.ArrayList;
import java.util.Map;

import sleepyweasel.purplefluffernutter.storage.LightStorage;

public class MovieEntryStorageVolatile implements MovieEntryStorage {

    LightStorage storage;

    public MovieEntryStorageVolatile() {
        storage = LightStorage.getInstance();
    }

    public boolean isEmpty() {
        return storage.size() == 0;
    }

    public void addMovie(MovieEntry entry) {
        storage.add(entry);
    }

    public int size() {
        return storage.size();
    }

    public MovieEntry getEntry(int id) {
        return storage.get(id);
    }

    public MovieEntry getEntryByTitle(String movieTitle) {
        for (Map.Entry<Integer,MovieEntry> entry : storage.entrySet()) {
            MovieEntry movie = entry.getValue();
            if (movie.getTitle().equals(movieTitle))
                return movie;
        }
        return null;
    }

    public void clear() {
        storage.clear();
    }

    public ArrayList<String> getMovieTitles() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (MovieEntry movie : storage.values()) {
            arrayList.add(movie.getTitle());
        }
        return arrayList;
    }
}
