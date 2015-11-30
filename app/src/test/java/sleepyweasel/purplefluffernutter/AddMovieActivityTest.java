package sleepyweasel.purplefluffernutter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.parceler.Parcels;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowSQLiteConnection;

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

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "movie_database.db";
        private static final String ID_COLUMN = "id INTEGER PRIMARY KEY AUTOINCREMENT";
        private static final String TITLE_COLUMN = " title TEXT NOT NULL";
        private static final String YEAR_COLUMN = " year INTEGER NOT NULL";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_DB_TABLE = " CREATE TABLE MOVIE_ENTRY" +
                    " ( " + ID_COLUMN + ", " + TITLE_COLUMN + ", " + YEAR_COLUMN + ");";
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    @Before
    public void setUp() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        databaseHelper = new DatabaseHelper(mainActivity.getBaseContext());
        database = databaseHelper.getWritableDatabase();
        activity = Robolectric.setupActivity(AddMovieActivity.class);
        ButterKnife.inject(this, activity);
//        ShadowSQLiteConnection.nativeOpen("sleepyweasel.purplefluffernutter.movie_database.db", 0, "label",true, true);

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

    @After
    public void tearDown() {
        databaseHelper.close();
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