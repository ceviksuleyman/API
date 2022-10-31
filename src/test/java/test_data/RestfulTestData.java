package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String, Object> expectedRestful(String firtname, String lastname, Integer totalprice, Boolean depositpaid, String checkin, String checkout, String additionalneeds) {

        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firtname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", additionalneeds);

        return expectedData;
    }
}
