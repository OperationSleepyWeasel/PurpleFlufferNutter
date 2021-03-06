package sleepyweasel.purplefluffernutter;

import android.widget.ListAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import sleepyweasel.purplefluffernutter.storage.MovieContentProvider;
import sleepyweasel.purplefluffernutter.storage.MovieEntryStorage;
import sleepyweasel.purplefluffernutter.storage.StorageUtils;

import static org.assertj.core.api.Assertions.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MovieListTest {

    private MovieEntryStorage storage = StorageUtils.getMovieStorage();

    private static final String MOVIE_TITLE = "Movie title";
    private static final int MOVIE_YEAR = 1994;

    @Before
    public void setUp() {
        ShadowContentResolver.registerProvider(MovieContentProvider.PROVIDER_NAME, (MovieContentProvider) storage);
        ((MovieContentProvider) storage).onCreate();
        storage.clear();
    }

    private ListAdapter getListAdapter() {
        MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);
        MovieListFragment movieListFragment = (MovieListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.movie_list);
        return movieListFragment.getListAdapter();
    }

    @Test
    public void shouldBeEmptyWhenNoMoviesOnList() throws Exception {
        ListAdapter adapter = getListAdapter();
        assertThat(adapter.isEmpty()).isTrue();
    }

    @Test
    public void shouldNotBeEmptyWhenNewItemAdded() throws Exception {
        storage.addMovie(new MovieEntry(MOVIE_TITLE, MOVIE_YEAR));

        ListAdapter adapter = getListAdapter();
        assertThat(adapter.isEmpty()).isFalse();
    }

    @Test
    public void shouldDisplayMovieTitleAsListElementLabel() throws Exception {
        storage.addMovie(new MovieEntry(MOVIE_TITLE, MOVIE_YEAR));

        ListAdapter adapter = getListAdapter();
        String label = adapter.getItem(0).toString();

        assertThat(label).isEqualTo(MOVIE_TITLE);
    }
}
