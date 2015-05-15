package sleepyweasel.purplefluffernutter;

import android.widget.ListAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MovieListTest {

    private MovieContentProvider contentProvider;

    private final String MOVIE_TITLE = "Movie title";

    @Before
    public void setUp() {
        contentProvider = MovieContentProvider.getInstance();
        contentProvider.clear();
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
        contentProvider.addMovie(new MovieEntry(MOVIE_TITLE));

        ListAdapter adapter = getListAdapter();
        assertThat(adapter.isEmpty()).isFalse();
    }

    @Test
    public void shouldDisplayMovieTitleAsListElementLabel() throws Exception {
        contentProvider.addMovie(new MovieEntry(MOVIE_TITLE));

        ListAdapter adapter = getListAdapter();
        String label = adapter.getItem(0).toString();

        assertThat(label).isEqualTo(MOVIE_TITLE);
    }
}
