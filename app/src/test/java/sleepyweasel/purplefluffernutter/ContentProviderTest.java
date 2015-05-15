package sleepyweasel.purplefluffernutter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ContentProviderTest {

    private MovieContentProvider contentProvider = new MovieContentProvider();

    private static final String MOVIE_TITLE_1 = "title 1";
    private static final String MOVIE_TITLE_2 = "title 2";

    private MovieEntry firstEntry = new MovieEntry(MOVIE_TITLE_1);
    private MovieEntry secondEntry = new MovieEntry(MOVIE_TITLE_2);

    @Test
    public void shouldCreateEmptyContentProvider() throws Exception {
        assertThat(contentProvider.isEmpty()).isTrue();
    }

    @Test
    public void shouldAddMovieEntryToContentProvider() throws Exception {
        contentProvider.addMovie(firstEntry);

        assertThat(contentProvider.isEmpty()).isFalse();
    }

    @Test
    public void shouldReturnSizeZeroIfContentProviderEmpty() throws Exception {
        assertThat(contentProvider.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnSizeTwoForContentProviderWithTwoMovies() throws Exception {
        contentProvider.addMovie(firstEntry);
        contentProvider.addMovie(firstEntry);

        assertThat(contentProvider.size()).isEqualTo(2);
    }

    @Test
    public void shouldGetMovieEntryById() throws Exception {
        contentProvider.addMovie(firstEntry);

        MovieEntry entry = contentProvider.getEntry(0);

        assertThat(entry.getTitle()).isEqualTo(MOVIE_TITLE_1);
    }

    @Test
    public void shouldGetSecondMovieEntry() throws Exception {
        contentProvider.addMovie(firstEntry);
        contentProvider.addMovie(secondEntry);

        MovieEntry entry = contentProvider.getEntry(1);

        assertThat(entry.getTitle()).isEqualTo(MOVIE_TITLE_2);
    }

    @Test
    public void shouldGetMovieEntryByTitle() throws Exception {
        contentProvider.addMovie(firstEntry);

        MovieEntry entry = contentProvider.getEntryByTitle(MOVIE_TITLE_1);

        assertThat(entry.getTitle()).isEqualTo(MOVIE_TITLE_1);
    }

    @Test
    public void shouldReturnNullIfMovieNotFoundByTitle() throws Exception {
        contentProvider.addMovie(firstEntry);
        contentProvider.addMovie(secondEntry);

        MovieEntry entry = contentProvider.getEntryByTitle("another title");

        assertThat(entry).isNull();
    }
}
