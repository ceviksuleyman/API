package TestData;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> expectedDataMethod(Integer userId, String title, Boolean completed) {

        Map<String, Object> expectedData = new HashMap<>();

        if (userId != null) expectedData.put("userId", userId);

        if (title != null) expectedData.put("title", title);

        if (completed != null) expectedData.put("completed", completed);

        return expectedData;
    }

    public String expectedDataInString(Integer userId, String title, Boolean completed) { // dinamik expected data method


        String expectedData = " {\n" +
                "                 \"userId\": " + userId + ",\n" +
                "                 \"title\": \"" + title + "\",\n" +
                "                 \"completed\": " + completed + "\n" +
                "                 }";

        return expectedData;
    }
}
