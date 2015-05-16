package sleepyweasel.purplefluffernutter;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sleepyweasel.purplefluffernutter.storage.StorageUtils;

import static org.assertj.core.api.Assertions.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class EditMovieActivityTest {

    @InjectView(R.id.button_save) Button saveButton;

    @InjectView(R.id.movie_title_value) TextView movieTitleTextView;

    void initActivity() {
        EditMovieActivity activity = Robolectric.setupActivity(EditMovieActivity.class);
        ButterKnife.inject(this, activity);
    }

    MovieEntryStorage getEmptyStorage() {
        MovieEntryStorage storage = StorageUtils.getMovieStorage();
        storage.clear();
        return storage;
    }

    @Test
    public void shouldOnClickSaveButtonAddMovieToStorage() throws Exception {
        initActivity();
        String movieTitle = movieTitleTextView.getText().toString();
        MovieEntryStorage storage = getEmptyStorage();

        saveButton.performClick();

        assertThat(storage.size()).isEqualTo(1);
        MovieEntry entry = storage.getEntry(0);
        assertThat(entry.getTitle()).isEqualTo(movieTitle);
    }

    //TODO: shouldNotSaveAMovieWithoutATitle
}
