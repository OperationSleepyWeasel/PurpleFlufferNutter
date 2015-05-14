package sleepyweasel.purplefluffernutter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ContentProviderTest {

    private MovieContentProvider contentProvider = new MovieContentProvider();

    private static final String MOVIE_TITLE = "title";

    @Test
    public void shouldCreateEmptyContentProvider() throws Exception {
        assertThat(contentProvider.isEmpty()).isTrue();
    }

    @Test
    public void shouldAddMovieEntryToContentProvider() throws Exception {
        contentProvider.addMovie(new MovieEntry(MOVIE_TITLE));

        assertThat(contentProvider.isEmpty()).isFalse();
    }

    @Test
    public void shouldReturnSizeZeroIfContentProviderEmpty() throws Exception {
        assertThat(contentProvider.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnSizeTwoForContentProviderWithTwoMovies() throws Exception {
        contentProvider.addMovie(new MovieEntry(MOVIE_TITLE));
        contentProvider.addMovie(new MovieEntry(MOVIE_TITLE));

        assertThat(contentProvider.size()).isEqualTo(2);
    }

    @Test
    public void shouldGetMovieEntryById() throws Exception {
        contentProvider.addMovie(new MovieEntry(MOVIE_TITLE));

        MovieEntry entry = contentProvider.getEntry(0);

        assertThat(entry.title).isEqualTo(MOVIE_TITLE);
    }
}
