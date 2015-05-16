package sleepyweasel.purplefluffernutter;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AddMovieActivityTest {

    private static final String QUERY = "query";

    @InjectView(R.id.webButton) Button button;
    @InjectView(R.id.titleTextField) EditText title;

    AddMovieActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(AddMovieActivity.class);
        ButterKnife.inject(this, activity);

        //TODO: try one of below methods (they seemed not working for me)
        //http://stackoverflow.com/questions/26939340/how-do-you-override-a-module-dependency-in-a-unit-test-with-dagger-2-0
        //https://github.com/chiuki/android-test-demo
        activity.tmdb = mock(Tmdb.class);
        //not using mock here because of @Parcel generation (it would be nice to find workaround)
        SearchResult searchResult = new SearchResult();
        searchResult.setResults(new ArrayList<Result>());
        when(activity.tmdb.searchMovie(anyString())).thenReturn(searchResult);
    }

    @Test
    public void shouldSearchMoviesInTmdbWithProperQuery() {
        title.setText(QUERY);

        button.performClick();

        verify(activity.tmdb).searchMovie(QUERY);
    }
}