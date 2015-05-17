package sleepyweasel.purplefluffernutter.storage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import sleepyweasel.purplefluffernutter.BuildConfig;
import sleepyweasel.purplefluffernutter.MovieEntry;
import sleepyweasel.purplefluffernutter.storage.MovieContentProvider;
import sleepyweasel.purplefluffernutter.storage.MovieEntryStorage;
import sleepyweasel.purplefluffernutter.storage.StorageUtils;

import static org.assertj.core.api.Assertions.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MovieEntryStorageTest {

    private MovieEntryStorage storage = StorageUtils.getMovieStorage();

    private static final String MOVIE_TITLE_1 = "title 1";
    private static final String MOVIE_TITLE_2 = "title 2";
    private static final int MOVIE_YEAR_1 = 1991;
    private static final int MOVIE_YEAR_2 = 2000;

    private MovieEntry firstEntry = new MovieEntry(MOVIE_TITLE_1, MOVIE_YEAR_1);
    private MovieEntry secondEntry = new MovieEntry(MOVIE_TITLE_2, MOVIE_YEAR_2);

    @Before
    public void setUp() throws Exception {
        ShadowContentResolver.registerProvider(MovieContentProvider.PROVIDER_NAME, (MovieContentProvider) storage);
        ((MovieContentProvider) storage).onCreate();
        storage.clear();
    }

    @Test
    public void shouldBeEmptyWhenNoMovieWasAdded() throws Exception {
        assertThat(storage.isEmpty()).isTrue();
    }

    @Test
    public void shouldAddMovieEntryToStorage() throws Exception {
        storage.addMovie(firstEntry);

        assertThat(storage.isEmpty()).isFalse();
    }

    @Test
    public void shouldClearStorage() throws Exception {
        storage.addMovie(secondEntry);

        storage.clear();

        assertThat(storage.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnSizeZeroIfStorageEmpty() throws Exception {
        assertThat(storage.size()).isZero();
    }

    @Test
    public void shouldReturnSizeTwoForStorageWithTwoMovies() throws Exception {
        storage.addMovie(firstEntry);
        storage.addMovie(firstEntry);

        assertThat(storage.size()).isEqualTo(2);
    }

    @Test
    public void shouldGetMovieEntryById() throws Exception {
        storage.addMovie(firstEntry);

        MovieEntry entry = storage.getEntry(1);

        assertThat(entry).isEqualTo(firstEntry);
    }

    @Test
    public void shouldGetSecondMovieEntry() throws Exception {
        storage.addMovie(firstEntry);
        storage.addMovie(secondEntry);

        MovieEntry entry = storage.getEntry(2);

        assertThat(entry).isEqualTo(secondEntry);
    }

    @Test
    public void shouldGetMovieEntryByTitle() throws Exception {
        storage.addMovie(firstEntry);

        MovieEntry entry = storage.getEntryByTitle(firstEntry.getTitle());

        assertThat(entry).isEqualTo(firstEntry);
    }

    @Test
    public void shouldReturnNullIfMovieNotFoundByTitle() throws Exception {
        storage.addMovie(firstEntry);
        storage.addMovie(secondEntry);

        MovieEntry entry = storage.getEntryByTitle("another title");

        assertThat(entry).isNull();
    }

    @Test
    @Ignore
    public void shouldGetOnlyOneStorageInstance() throws Exception {
        storage.addMovie(firstEntry);

        MovieEntryStorage anotherStorage = StorageUtils.getMovieStorage();

        MovieEntry entry = anotherStorage.getEntryByTitle(firstEntry.getTitle());
        assertThat(entry).isEqualTo(firstEntry);
    }
}
