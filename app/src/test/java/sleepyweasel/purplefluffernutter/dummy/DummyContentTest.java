package sleepyweasel.purplefluffernutter.dummy;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DummyContentTest {

    private static final String RETURN_STRING = "tralalala";

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

    @Test
    public void shouldUseMock() {
        DummyContent dummyContent = mock(DummyContent.class);

        when(dummyContent.toString()).thenReturn(RETURN_STRING);

        assertThat(dummyContent.toString()).isEqualTo(RETURN_STRING);
    }
}