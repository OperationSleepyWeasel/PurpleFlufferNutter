package sleepyweasel.purplefluffernutter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ContentProviderTest {

    @Test
    public void shouldBeEmptyWhenNoMoviesOnList() throws Exception {
        MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);
        MovieListFragment movieListFragment = (MovieListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.movie_list);
        int itemPosition = 0;
        String title = movieListFragment.getListAdapter().getItem(itemPosition).toString();

        assertThat(title).isEqualTo("");
    }

//    @Test
//    public void shouldDisplayProperNumberOfListElements() throws Exception {
//        MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);
//    }

//    @Test
//    public void shouldLoadMovieTitlesToList() throws Exception {
//        MovieListActivity activity = Robolectric.setupActivity(MovieListActivity.class);
//        assertTrue(true);
//    }
}
