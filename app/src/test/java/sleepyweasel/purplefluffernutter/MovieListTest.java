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

    @Test
    public void shouldBeEmptyWhenNoMoviesOnList() throws Exception {
        MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);
        MovieListFragment movieListFragment = (MovieListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.movie_list);
        ListAdapter adapter = movieListFragment.getListAdapter();

        assertThat(adapter.isEmpty()).isTrue();
    }

//    @Test
//    public void shouldNotBeEmptyWhenNewItemAdded() throws Exception {
//        MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);
//        MovieListFragment movieListFragment = (MovieListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.movie_list);
//        ArrayAdapter adapter = (ArrayAdapter) movieListFragment.getListAdapter();
//        MovieContentProvider contentProvider = new MovieContentProvider();
//
//        adapter.add("title");
//
//        assertFalse(adapter.isEmpty());
//    }
}
