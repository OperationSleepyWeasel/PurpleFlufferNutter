package sleepyweasel.purplefluffernutter;

import android.database.Cursor;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import java.util.ArrayList;

import sleepyweasel.purplefluffernutter.storage.MovieContentProvider;

import static org.assertj.core.api.Assertions.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MovieContentProviderTest {

    private static final String MOVIE_TITLE_1 = "Movie title";
    private static final int MOVIE_YEAR_1 = 1991;
    private static final String MOVIE_TITLE_2 = "Movie title 2";
    private static final int MOVIE_YEAR_2 = 2000;

    private MovieContentProvider storage;
    private MovieEntry firstEntry = new MovieEntry(MOVIE_TITLE_1, MOVIE_YEAR_1);
    private MovieEntry secondEntry = new MovieEntry(MOVIE_TITLE_2, MOVIE_YEAR_2);

    @Before
    public void setUp() {
        storage = new MovieContentProvider();
        ShadowContentResolver.registerProvider(MovieContentProvider.PROVIDER_NAME, storage);
    }

    private void createStorageWithOneMovie() {
        storage.onCreate();
        storage.addMovie(firstEntry);
    }

    private void createStorageWithTwoMovies() {
        storage.onCreate();
        storage.addMovie(firstEntry);
        storage.addMovie(secondEntry);
    }

    private Uri createUriToAllMovies() {
        String URL = "content://" + MovieContentProvider.PROVIDER_NAME + "/movies";
        Uri uri = Uri.parse(URL);
        return uri;
    }

    private MovieEntry createMovieEntryFromCursorData(Cursor cursor) {
        String titleValue = cursor.getString(cursor.getColumnIndex(MovieContentProvider.TITLE_COLUMN_NAME));
        int yearValue = cursor.getInt(cursor.getColumnIndex(MovieContentProvider.YEAR_COLUMN_NAME));
        return new MovieEntry(titleValue, yearValue);
    }

    @Test
    public void shouldCreateWriteableDatabaseOnCreation() {
        assertThat(storage.onCreate()).isTrue();
    }

    @Test
    public void shouldBeAbleToAccessFirstDatabaseEntryWithQueryForAllMovies() {
        createStorageWithOneMovie();
        Uri uri = createUriToAllMovies();

        Cursor cursor = storage.query(uri, null, null, null, null);

        assertThat(cursor.moveToFirst()).isTrue();
    }

    @Test
    public void shouldQueryDatabaseForTheFirstMovie() {
        createStorageWithOneMovie();
        Uri uri = createUriToAllMovies();

        Cursor cursor = storage.query(uri, null, null, null, null);
        cursor.moveToFirst();

        int idValue = cursor.getInt(cursor.getColumnIndex(MovieContentProvider._ID_COLUMN_NAME));
        assertThat(idValue).isEqualTo(1);
        MovieEntry entry = createMovieEntryFromCursorData(cursor);
        assertThat(entry).isEqualTo(firstEntry);
    }

    @Test
    public void shouldCreateQueryToGetEveryDatabaseRecord() {
        createStorageWithTwoMovies();
        Uri uri = createUriToAllMovies();

        Cursor cursor = storage.query(uri, null, null, null, null);

        cursor.moveToFirst();
        MovieEntry entry = createMovieEntryFromCursorData(cursor);
        assertThat(entry).isEqualTo(firstEntry);
        cursor.moveToNext();
        entry = createMovieEntryFromCursorData(cursor);
        assertThat(entry).isEqualTo(secondEntry);
    }
}
