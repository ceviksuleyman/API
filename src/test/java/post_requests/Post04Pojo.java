package post_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.bookingPojo.BookingDatesPojo;
import pojos.bookingPojo.BookingPojo;
import pojos.bookingPojo.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;

public class Post04Pojo extends RestfulBaseUrl {

    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void post04() {

        //set the url
        spec.pathParam("1", "booking");

        //set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 999, true, bookingDatesPojo, "Breakfast");

        System.out.println("expectedData = " + expectedData);

        // send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        // do assertion
        BookingResponseBodyPojo actualData = response.as(BookingResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        Assert.assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        Assert.assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        Assert.assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        Assert.assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        Assert.assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

    }
}
