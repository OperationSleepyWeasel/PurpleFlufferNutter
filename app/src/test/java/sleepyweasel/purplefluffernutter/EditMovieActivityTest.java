package sleepyweasel.purplefluffernutter;

import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class EditMovieActivityTest {

    @Test
    public void shouldOnClickSaveButtonAddMovieToContentProvider() throws Exception {
        EditMovieActivity activity = Robolectric.setupActivity(EditMovieActivity.class);
        Button saveButton = (Button) activity.findViewById(R.id.button_save);
        MovieContentProvider contentProvider = MovieContentProvider.getInstance();
        contentProvider.clear();

        saveButton.performClick();

        assertThat(contentProvider.isEmpty()).isFalse();
    }

    //TODO: shouldNotSaveAMovieWithoutATitle
}
