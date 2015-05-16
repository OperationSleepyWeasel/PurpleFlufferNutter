package sleepyweasel.purplefluffernutter.rest.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String elementAsString = json.getAsString();
        if (elementAsString == null || elementAsString.equals("")) {
            return null;
        } else {
            try {
                return FORMAT.parse(elementAsString);
            } catch (ParseException e) {
                return null;
            }
        }
    }
}
