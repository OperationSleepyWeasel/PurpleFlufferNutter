package sleepyweasel.purplefluffernutter;

import android.database.Cursor;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import sleepyweasel.purplefluffernutter.storage.MovieContentProvider;

import static org.assertj.core.api.Assertions.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MovieContentProviderTest {

    private MovieContentProvider storage;

    @Before
    public void setUp() {
        storage = new MovieContentProvider();
        ShadowContentResolver.registerProvider(MovieContentProvider.PROVIDER_NAME, storage);
    }

    @Test
    public void shouldCreateWriteableDatabaseOnCreation() {
        assertThat(storage.onCreate()).isTrue();
    }

    @Test
    public void shouldBeAbleToAccessFirstDatabaseEntryWithQueryForAllMovies() {
        storage.onCreate();
        storage.addMovie(new MovieEntry("Movie title", 1991));

        String URL = "content://" + MovieContentProvider.PROVIDER_NAME + "/movies";
        Uri uri = Uri.parse(URL);
        Cursor cursor = storage.query(uri, null, null, null, null);

        assertThat(cursor.moveToFirst()).isTrue();
    }

}
