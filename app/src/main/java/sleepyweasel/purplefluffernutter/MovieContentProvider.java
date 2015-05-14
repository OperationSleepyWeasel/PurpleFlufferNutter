package sleepyweasel.purplefluffernutter;

public class MovieContentProvider {

    private boolean hasMovie;

    public MovieContentProvider() {
        hasMovie = false;
    }

    public boolean isEmpty() {
        return !hasMovie;
    }

    public void addMovie(MovieEntry title) {
        hasMovie = true;
    }
}
