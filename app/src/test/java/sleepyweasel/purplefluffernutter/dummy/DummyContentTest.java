package sleepyweasel.purplefluffernutter.dummy;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class DummyContentTest {

    @Test
    public void shouldRun() {
        assertTrue(true);
    }

    @Test
    public void shouldRun2() {
        assertFalse(false);
    }

    @Test
    public void shouldRunWithAssertJ() {
        assertThat(true).isTrue();
    }
}