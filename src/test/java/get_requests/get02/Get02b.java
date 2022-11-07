package get_requests.get02;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02b extends ReqresBaseUrl {
    /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty
  */

    @Test
    public void get02b() {

        spec.pathParams("first", "users", "second", 23);


        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        System.out.println("statusCode : " + response.statusCode());
        assertEquals(404, response.statusCode());  // HTTP Status code should be 404


        System.out.println("statusLine : " + response.statusLine());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine()); //  Status Line should be HTTP/1.1 404 Not Found


        System.out.println("Server : " + response.header("Server"));
        assertEquals("cloudflare", response.header("Server")); // Server is "cloudflare"


        assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]", "").length()); // Response body should be empty
        assertEquals(0, response.asString().replaceAll("\\W", "").length()); // Response body should be empty
        assertEquals(2, response.asString().replaceAll("\\s", "").length()); // Response body should be empty
        assertEquals("", response.asString().replaceAll("\\W", "")); // Response body should be empty

    }
}
