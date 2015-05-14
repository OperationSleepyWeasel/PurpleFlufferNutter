package sleepyweasel.purplefluffernutter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ContentProviderTest {

    private MovieContentProvider contentProvider = new MovieContentProvider();

    @Test
    public void shouldCreateEmptyContentProvider() throws Exception {
        assertTrue(contentProvider.isEmpty());
    }

    @Test
    public void shouldAddMovieEntryToContentProvider() throws Exception {
        contentProvider.addMovie(new MovieEntry("title"));

        assertFalse(contentProvider.isEmpty());
    }

    @Test
    public void shouldReturnSizeZeroIfContentProviderEmpty() throws Exception {
        assertThat(contentProvider.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnSizeTwoForContentProviderWithTwoMovies() throws Exception {
        contentProvider.addMovie(new MovieEntry("title"));
        contentProvider.addMovie(new MovieEntry("title"));

        assertThat(contentProvider.size()).isEqualTo(2);
    }
}
