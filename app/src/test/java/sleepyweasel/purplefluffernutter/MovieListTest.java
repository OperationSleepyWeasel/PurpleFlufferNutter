package sleepyweasel.purplefluffernutter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListAdapter;

import org.junit.After;
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
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        databaseHelper = new DatabaseHelper(activity.getBaseContext());
        database = databaseHelper.getWritableDatabase();
//        ShadowContentResolver.registerProvider(MovieContentProvider.PROVIDER_NAME, (MovieContentProvider) storage);
//        ((MovieContentProvider) storage).onCreate();
        storage.clear();
    }

    @After
    public void tearDown() {
        databaseHelper.close();
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
