package sleepyweasel.purplefluffernutter;

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

    @Test
    public void shouldCreateWriteableDatabaseOnCreation() {
        MovieContentProvider storage = new MovieContentProvider();
        ShadowContentResolver.registerProvider("sleepyweasel.purplefluffernutter.MoviesProvider", storage);
        assertThat(storage.onCreate()).isTrue();
    }

}
