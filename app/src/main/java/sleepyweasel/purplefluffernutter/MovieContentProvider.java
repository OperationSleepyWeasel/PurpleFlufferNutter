package sleepyweasel.purplefluffernutter;

public class MovieContentProvider {

    private int size;

    public MovieContentProvider() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addMovie(MovieEntry title) {
        size++;
    }

    public int size() {
        return size;
    }
}
