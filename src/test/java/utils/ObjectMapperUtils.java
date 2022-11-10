package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    //new ObjectMapper().readValue(jsonInString, HashMap.class);

    private static ObjectMapper mapper; //final

    static {

        mapper = new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls) { // Generic -> Method json'i java'ya cevir

        T javaResult = null;
        //T javaResult;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;
    }

    //2.method java objesini json dataya cevirir (serialization)

    public static String convertJavaObjectToJson(Object obj) {

        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
