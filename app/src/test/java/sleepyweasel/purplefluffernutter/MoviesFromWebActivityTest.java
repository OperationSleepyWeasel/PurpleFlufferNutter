package sleepyweasel.purplefluffernutter;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;

import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MoviesFromWebActivityTest {

    private MoviesFromWebActivity activity;

    @Test
    public void shouldCreateSearchResultFromIntent() {
        SearchResult searchResult = new SearchResult();
        searchResult.setResults(Collections.<Result>emptyList());

        Intent i = new Intent(RuntimeEnvironment.application.getApplicationContext(), MoviesFromWebActivity.class);
        i.putExtra(AddMovieActivity.FOUND_MOVIES_ID, Parcels.wrap(searchResult));

        activity = Robolectric.buildActivity(MoviesFromWebActivity.class).withIntent(i).create().get();

        assertThat(activity.searchResult).isEqualTo(searchResult);
    }
}