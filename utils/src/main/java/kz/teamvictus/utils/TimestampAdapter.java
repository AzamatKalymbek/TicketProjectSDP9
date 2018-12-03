package kz.teamvictus.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.TimeZone;

public class TimestampAdapter implements JsonDeserializer {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Timestamp deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String dateStr = element.getAsString();

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date date = dateFormat.parse(dateStr);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }

//    @Override
//    public JsonElement serialize(Timestamp timestamp, Type type, JsonSerializationContext jsonSerializationContext) {
//        String formattedDate = dateFormat.format(timestamp);
//        return new JsonPrimitive(formattedDate);
//    }
}
