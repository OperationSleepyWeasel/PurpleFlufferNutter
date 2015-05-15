package sleepyweasel.purplefluffernutter;

import android.widget.ListAdapter;

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

    private MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);

    private MovieListFragment movieListFragment = (MovieListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.movie_list);

    private ListAdapter adapter = movieListFragment.getListAdapter();

    @Test
    public void shouldBeEmptyWhenNoMoviesOnList() throws Exception {
        assertThat(adapter.isEmpty()).isTrue();
    }

    @Test
    public void shouldNotBeEmptyWhenNewItemAdded() throws Exception {
        MovieContentProvider contentProvider = MovieContentProvider.getInstance();

        contentProvider.addMovie(new MovieEntry("Movie title"));

        assertThat(adapter.isEmpty()).isFalse();
    }
}
