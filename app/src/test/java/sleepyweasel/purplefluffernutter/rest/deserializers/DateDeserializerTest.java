package sleepyweasel.purplefluffernutter.rest.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateDeserializerTest {
    private static final String DATE_STRING = "1999-10-14";
    private static final Date EXPECTED_DATE = new GregorianCalendar(1999, 9, 14).getTime();

    @Mock
    private JsonElement element;

    @Mock
    private JsonDeserializationContext context;

    private DateDeserializer dateDeserializer = new DateDeserializer();

    @Test
    public void shouldDeserializeDate() {
        when(element.getAsString()).thenReturn(DATE_STRING);

        Date date = dateDeserializer.deserialize(element, Date.class, context);

        assertThat(date).isEqualTo(EXPECTED_DATE);
    }

    @Test
    public void shouldHandleEmptyString() {
        when(element.getAsString()).thenReturn("");

        Date date = dateDeserializer.deserialize(element, Date.class, context);

        assertThat(date).isNull();
    }

    @Test
    public void shouldHandleNull() {
        when(element.getAsString()).thenReturn(null);

        Date date = dateDeserializer.deserialize(element, Date.class, context);

        assertThat(date).isNull();
    }
}