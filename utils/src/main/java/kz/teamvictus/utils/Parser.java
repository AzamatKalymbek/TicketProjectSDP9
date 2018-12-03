package kz.teamvictus.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Parser {

    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final static Gson GSON = new GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .registerTypeAdapter(java.util.Date.class, new TimestampAdapter())
            .registerTypeAdapter(java.sql.Date.class, new TimestampAdapter())
            .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
            .setPrettyPrinting()
            .create();

    /**
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static String toJson(Object object) throws Exception {
        return GSON.toJson(object);
    }

    public static JsonObject parse(String jsonString) throws Exception {
        JsonParser parser = new JsonParser();
        return parser.parse(jsonString).getAsJsonObject();
    }

    /**
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static JsonElement toJsonTree(Object object) throws Exception {
        return GSON.toJsonTree(object);
    }

    /**
     *
     * @param object
     * @param type
     * @return
     * @throws Exception
     */
    public static JsonElement toJsonTree(Object object, Type type) throws Exception {
        return GSON.toJsonTree(object, type);
    }

    /**
     *
     * @param <T>
     * @param data
     * @param valueType
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String data, Class<T> valueType) throws Exception {
        return GSON.fromJson(data, valueType);
    }

    /**
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static Object fromJson(String json) throws Exception {
        return fromJson(json, Object.class);
    }

    /**
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static HashMap fromJsonMap(String json) throws Exception {
        return fromJson(json, HashMap.class);
    }

    /**
     *
     * @param json String
     * @param mapType example new TypeToken&LT;<b>HashMap&LT;String, Long&GT;</b>&GT;(){}.getType()
     * @return example <b>HashMap&LT;String, Long&GT;</b>
     * @throws Exception
     */
    public static HashMap fromJsonMap(String json, Type mapType) throws Exception {
        return GSON.fromJson(json, mapType);
    }

    /**
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static ArrayList fromJsonList(String json) throws Exception {
        return fromJson(json, ArrayList.class);
    }

    /**
     *
     * @param json String
     * @param listType example new TypeToken&LT;<b>List&LT;Long&GT;</b>&GT;(){}.getType()
     * @return example  <b>List&LT;Long&GT;</b>
     * @throws Exception
     */
    public static ArrayList fromJsonList(String json, Type listType) throws Exception {
        return GSON.fromJson(json, listType);
    }

    /**
     *
     * @param <T>
     * @param data
     * @param valueType
     * @return
     * @throws Exception
     */
    public static <T> T convert(Object data, Class<T> valueType) throws Exception {
        return GSON.fromJson(toJson(data), valueType);
    }

}

