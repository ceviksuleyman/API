package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {


    public Map<String, Object> expectedDataMethod(String name, Integer year, String color, String pantone_value) {

        Map<String, Object> data = new HashMap<>();

        data.put("name", name);
        data.put("year", year);
        data.put("color", color);
        data.put("pantone_value", pantone_value);

        return data;
    }

    public Map<String, String> expectedSupportMethod(String url, String text) {

        Map<String, String> support = new HashMap<>();

        support.put("url", url);
        support.put("text", text);

        return support;
    }
    public String expectedDataInStringReqres(String name, String job) {

        String jsonInString = "{\n" +
                "                \"name\": \"" + name + "\",\n" +
                "                \"job\": \"" + job + "\"\n" +
                "                }";
        return jsonInString;
    }

}
