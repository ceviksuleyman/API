package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
    And
        Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
    */

    @Test
    public void get05() {


        // 1. Set The Url
        spec.pathParam("first", "booking").queryParams("firstname", "Ali", "lastname", "Cengiz");
        // https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz
        // key=value seklinde calisiyor


        // 2. Set the Expected Data


        // 3. Send The Request And Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        // 4. Do Assertion
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
