package get_requests.session02;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get04b extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
    When
        User sends get request to the URL
    Then
        Status code is 200
    And
        Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"
    */

    @Test
    public void get04b() {

        spec.pathParams("first", "booking").queryParams("firstname", "Almedin", "lastname", "Alikadic");
        //https://restful-booker.herokuapp.com/booking/?firstname=Almedin&lastname=Alikadic

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
