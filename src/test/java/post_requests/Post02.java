package post_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import TestData.RestfulTestData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
/*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "Murtaza",
            "lastname": "Can",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
                         "bookingid": 5315,
                         "booking": {
                             "firstname": "Murtaza",
                             "lastname": "Can",
                             "totalprice": 11111,
                             "depositpaid": true,
                             "bookingdates": {
                                 "checkin": "2021-09-09",
                                 "checkout": "2021-09-21"
                             }
                         }
                      }
*/

    @Test
    public void post02() throws FileNotFoundException {

        // 1. Set The URL
        spec.pathParam("first", "booking");


        // 2. Set The Expected Data ( put, post, patch)
        RestfulTestData testData = new RestfulTestData();
        Map<String, String> bookingDates = testData.bookingDatesMethod("2021-09-09", "2021-09-21");  // bookingDates
        Map<String, Object> expectedData = testData.expectedDataMethod("Murtaza",
                "Can",
                11111,
                true,
                bookingDates); // Gonderdigim data Payload
        System.out.println("expectedData = " + expectedData);


        // JSONObject ile post
        File file = new File("src/test/java/TestData/postBody.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject expectedData1 = new JSONObject(jsonTokener);
        System.out.println("expectedData1 = " + expectedData1.toString());

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData1.toString()).when().post("/{first}");
        response.prettyPrint();


        // 4. Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); //
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));

        assertEquals(expectedData.get("bookingdates.checkin"), ((Map) actualData.get("booking")).get("bookingdates.checkin"));

        assertEquals(bookingDates.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));




        // JSONObject
        assertEquals(expectedData1.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData1.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData1.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData1.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));

        assertEquals(expectedData1.getJSONObject("bookingdates").getString("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedData1.getJSONObject("bookingdates").getString("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
    }
}
