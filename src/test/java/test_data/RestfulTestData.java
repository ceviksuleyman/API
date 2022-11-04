package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String, String> bookingDatesMethod(String checkin, String checkout) {

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkin);
        bookingDatesMap.put("checkout", checkout);

        return bookingDatesMap;
    }

    public Map<String, Object> expectedDataMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates) {

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstname);
        booking.put("lastname", lastname);
        booking.put("totalprice", totalprice);
        booking.put("depositpaid", depositpaid);
        booking.put("bookingdates", bookingdates);

        return booking;
    }
}
