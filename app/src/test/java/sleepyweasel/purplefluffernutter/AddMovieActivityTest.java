package sleepyweasel.purplefluffernutter;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.parceler.Parcels;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowIntent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AddMovieActivityTest {

    private static final String QUERY = "query";
    private static final String TITLE = "title";
    private static final Date RELEASE_DATE = new Date();

    @InjectView(R.id.webButton) Button button;
    @InjectView(R.id.titleTextField) EditText title;

    private AddMovieActivity activity;
    private SearchResult searchResult;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(AddMovieActivity.class);
        ButterKnife.inject(this, activity);

        //TODO: try one of below methods (they seemed not working for me)
        //http://stackoverflow.com/questions/26939340/how-do-you-override-a-module-dependency-in-a-unit-test-with-dagger-2-0
        //https://github.com/chiuki/android-test-demo
        activity.tmdb = mock(Tmdb.class);

        //not using mock here because of @Parcel generation (it would be nice to find workaround)
        searchResult = new SearchResult();
        searchResult.setResults(Collections.<Result>emptyList());
        //not using mock because Response class is final...
        final Response response = new Response("url", 200, "ok", Collections.<Header>emptyList(), mock(TypedInput.class));
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callback<SearchResult>) invocation.getArguments()[1]).success(searchResult, response);
                return null;
            }
        }).when(activity.tmdb).searchMovie(anyString(), Matchers.<Callback<SearchResult>>any());
    }

    @Test
    public void shouldSearchMoviesInTmdbWithProperQuery() {
        title.setText(QUERY);

        button.performClick();

        verify(activity.tmdb).searchMovie(eq(QUERY), Matchers.<Callback<SearchResult>>any());
    }

    @Test
    public void shouldPassSearchMoviesResultsToNextActivity() {
        List<Result> results = Arrays.asList(new Result(TITLE, RELEASE_DATE));
        searchResult.setResults(results);
        title.setText(QUERY);

        button.performClick();

        ShadowIntent shadowIntent = Shadows.shadowOf(Shadows.shadowOf(activity).getNextStartedActivity());
        assertThat(shadowIntent.getComponent().getClassName()).isEqualTo(MoviesFromWebActivity.class.getName());
        assertThat(shadowIntent.getExtras().containsKey(AddMovieActivity.FOUND_MOVIES_ID)).isTrue();

        SearchResult searchResultPassed = Parcels.unwrap(shadowIntent.getExtras().getParcelable(AddMovieActivity.FOUND_MOVIES_ID));
        assertThat(searchResultPassed).isEqualTo(searchResult);
    }
}