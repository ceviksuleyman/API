package get_requests.session02;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.bookingPojo.BookingDatesPojo;
import pojos.bookingPojo.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends RestfulBaseUrl {
     /*
     Given
         https://restful-booker.herokuapp.com/booking/15
     When
         I send GET Request to the URL
     Then
         Status code is 200
     And
         Response body is like:
     {
    "firstname": "Fabio",
    "lastname": "Colque",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
  */

    @Test
    public void get12Pojo() {

        // set the url
        spec.pathParams("1", "booking", "2", "15");


        // set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        System.out.println("datesPojo = " + bookingDatesPojo);

        BookingPojo expectedData = new BookingPojo("Fabio", "Colque", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);


        // send the request and get the response
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();


        // Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class); // De-serialization
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        //1.yontem
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());


        //2.yontem
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
    }
}
