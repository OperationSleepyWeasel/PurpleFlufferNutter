package sleepyweasel.purplefluffernutter;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ContentProviderTest {

    private MovieEntryStorage contentProvider = MovieEntryStorage.getInstance();

    private static final String MOVIE_TITLE_1 = "title 1";
    private static final String MOVIE_TITLE_2 = "title 2";

    private MovieEntry firstEntry = new MovieEntry(MOVIE_TITLE_1);
    private MovieEntry secondEntry = new MovieEntry(MOVIE_TITLE_2);

    @Before
    public void setUp() throws Exception {
        contentProvider.clear();
    }

    @Test
    public void shouldBeEmptyWhenNoMovieWasAdded() throws Exception {
        assertThat(contentProvider.isEmpty()).isTrue();
    }

    @Test
    public void shouldAddMovieEntryToContentProvider() throws Exception {
        contentProvider.addMovie(firstEntry);

        assertThat(contentProvider.isEmpty()).isFalse();
    }

    @Test
    public void shouldClearContentProvider() throws Exception {
        contentProvider.addMovie(secondEntry);

        contentProvider.clear();

        assertThat(contentProvider.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnSizeZeroIfContentProviderEmpty() throws Exception {
        assertThat(contentProvider.size()).isZero();
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

        assertThat(entry).isEqualTo(firstEntry);
    }

    @Test
    public void shouldGetSecondMovieEntry() throws Exception {
        contentProvider.addMovie(firstEntry);
        contentProvider.addMovie(secondEntry);

        MovieEntry entry = contentProvider.getEntry(1);

        assertThat(entry).isEqualTo(secondEntry);
    }

    @Test
    public void shouldGetMovieEntryByTitle() throws Exception {
        contentProvider.addMovie(firstEntry);

        MovieEntry entry = contentProvider.getEntryByTitle(firstEntry.getTitle());

        assertThat(entry).isEqualTo(firstEntry);
    }

    @Test
    public void shouldReturnNullIfMovieNotFoundByTitle() throws Exception {
        contentProvider.addMovie(firstEntry);
        contentProvider.addMovie(secondEntry);

        MovieEntry entry = contentProvider.getEntryByTitle("another title");

        assertThat(entry).isNull();
    }

    @Test
    public void shouldGetOnlyOneContentProviderInstance() throws Exception {
        contentProvider.addMovie(firstEntry);

        MovieEntryStorage anotherContentProvider = MovieEntryStorage.getInstance();

        MovieEntry entry = anotherContentProvider.getEntryByTitle(firstEntry.getTitle());
        assertThat(entry).isEqualTo(firstEntry);
    }
}
