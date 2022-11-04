package get_requests.session02;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class post123 extends RestfulBaseUrl {

    @Test
    public void post123() {


        // set the url
        spec.pathParam("1", "booking");


        // set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        System.out.println("datesPojo = " + bookingDatesPojo);

        BookingPojo expectedData = new BookingPojo("ramo", "can", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{1}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
        json.prettyPrint();
    }
}
