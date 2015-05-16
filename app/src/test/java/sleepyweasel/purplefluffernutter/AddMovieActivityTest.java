package sleepyweasel.purplefluffernutter;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;
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
        activity.tmdb = mock(Tmdb.class);
        when(activity.tmdb.searchMovie(anyString())).thenReturn(mock(SearchResult.class));
    }

    @Test
    public void shouldCallTmdbWithProperQuery() {
        title.setText(QUERY);

        button.performClick();

        verify(activity.tmdb).searchMovie(QUERY);
    }
}