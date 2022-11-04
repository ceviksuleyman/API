package post_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

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
    public void post02() {

        // 1. Set The URL
        spec.pathParam("first", "booking");


        // 2. Set The Expected Data ( put, post, patch)
        RestfulTestData testData = new RestfulTestData();

        Map<String, String> bookingDates = testData.bookingDatesMethod("2021-09-09", "2021-09-21");  // bookingDates

        Map<String, Object> expectedData = testData.expectedDataMethod("Murtaza", "Can", 11111, true, bookingDates); // Gonderdigim data Payload

        System.out.println("expectedData = " + expectedData);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}"); //De-seri
        response.prettyPrint();


        // 4. Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));

        Assert.assertEquals(bookingDates.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(bookingDates.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));

    }
}
