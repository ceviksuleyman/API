package get_requests.session02;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class Get12Pojo extends RestfulBaseUrl {
     /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
         I send GET Request to the URL
     Then
         Status code is 200
     And
         Response body is like:
                       {
    "firstname": "Dane",
    "lastname": "Combs",
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
        spec.pathParams("1", "booking", "2", "18");


        // set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        System.out.println("datesPojo = " + bookingDatesPojo);

        BookingPojo expectedData = new BookingPojo("Dane", "Combs", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);


        // send the request and get the response
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();


        // Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class); // De-serialization
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        Assert.assertEquals(expectedData.getLastname(), actualData.getLastname());
        Assert.assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        Assert.assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        Assert.assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        //1.yontem
        Assert.assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        Assert.assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        //2.yontem
        Assert.assertEquals(expectedData.getBookingdates().getCheckin(), bookingDatesPojo.getCheckin());
        Assert.assertEquals(expectedData.getBookingdates().getCheckout(), bookingDatesPojo.getCheckout());

    }
}
