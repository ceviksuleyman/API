package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
 */

    @Test
    public void get09() {

        // Set the Url
        spec.pathParams("first", "booking", "second", 91);


        // Set the expected Data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2013-02-23");
        bookingdatesMap.put("checkout", "2014-10-23");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println(expectedData);


        // testdata class'dan obje
        RestfulTestData restfulTestData = new RestfulTestData();
        Map<String, Object> expectedDataMap = restfulTestData.expectedRestful(
                "Sally",
                "Brown",
                111,
                true,
                "2013-02-23",
                "2014-10-23",
                "Breakfast");
        System.out.println(expectedDataMap);


        // Send The Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        // Key-Value ikilileri String-Object şeklinde olduğundan, bookingdate value kısmını casting ile Map yaptık.
        assertEquals(bookingdatesMap.get("checkin"), ((Map<?, ?>) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));


        // obje ile assertion
        assertEquals(expectedDataMap.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualData.get("additionalneeds"));
        // Key-Value ikilileri String-Object şeklinde olduğundan, bookingdate value kısmını casting ile Map yaptık.
        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkin"), ((Map<?, ?>) actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"), ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));
    }
}
