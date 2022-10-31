package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Task02 extends GoRestBaseUrl {

     /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
   {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
  }
  */

    @Test
    public void task02() {

        //Set the Url
        spec.pathParams("first", "users", "second", "2986");


        //
        Response response = given().spec(spec).when().get("/{first}/{second}");


        JsonPath json = response.jsonPath();
        json.prettyPrint();

        assertNull(json.getString("meta"));
        assertEquals("Navin Talwar", json.getString("data.name"));
        assertEquals("navin_talwar@mclaughlin.name", json.getString("data.email"));
        assertEquals("male", json.getString("data.gender"));
        assertEquals("inactive", json.getString("data.status"));
        assertEquals(200, response.statusCode());

    }
}
