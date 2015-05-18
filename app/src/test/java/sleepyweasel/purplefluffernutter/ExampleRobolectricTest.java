package sleepyweasel.purplefluffernutter;

import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleRobolectricTest {

    @Test
    public void shouldReturnButtonsText() throws Exception {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        Button button = (Button) activity.findViewById(R.id.listButton);

        assertThat(button.getText().toString()).isEqualTo("List");
    }
}
