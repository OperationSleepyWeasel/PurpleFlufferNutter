package sleepyweasel.purplefluffernutter;

import org.junit.Test;

import sleepyweasel.purplefluffernutter.storage.MovieContentProvider;

import static org.assertj.core.api.Assertions.*;

public class MovieContentProviderTest {

    @Test
    public void shouldCreateWriteableDatabaseOnCreation() {
        MovieContentProvider storage = new MovieContentProvider();
        assertThat(storage.onCreate()).isTrue();
    }

}
