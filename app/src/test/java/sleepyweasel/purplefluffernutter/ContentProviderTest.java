package sleepyweasel.purplefluffernutter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ContentProviderTest {

    @Test
    public void shouldCreateEmptyContentProvider() throws Exception {
        MovieContentProvider contentProvider = new MovieContentProvider();

        assertTrue(contentProvider.isEmpty());
    }

    @Test
    public void shouldAddMovieEntryToContentProvider() throws Exception {
        MovieContentProvider contentProvider = new MovieContentProvider();
        contentProvider.addMovie(new MovieEntry("title"));

        assertFalse(contentProvider.isEmpty());
    }

    @Test
    public void shouldReturnSizeZeroIfContentProviderEmpty() throws Exception {
        MovieContentProvider contentProvider = new MovieContentProvider();

        assertThat(contentProvider.size()).isEqualTo(0);
    }
}
