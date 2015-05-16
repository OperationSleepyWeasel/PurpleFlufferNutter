package sleepyweasel.purplefluffernutter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MovieEntryStorage {

    public boolean isEmpty();

    public void addMovie(MovieEntry entry);

    public int size();

    public MovieEntry getEntry(int id);

    public MovieEntry getEntryByTitle(String movieTitle);

    public void clear();

    public ArrayList<String> getMovieTitles();
}
