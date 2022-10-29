package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03b extends ReqresBaseUrl {

    /*
   Given
       https://reqres.in/api/users/2
   When
       User send GET Request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       "email" is "janet.weaver@reqres.in",
   And
       "first_name" is "Janet"
   And
       "last_name" is "Weaver"
   And
       "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
   */

    @Test
    public void get03b() {

        //Set the Url
        spec.pathParams("first", "users", "second", 2);

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat() // assertThat olmadan da olur !!!!!!
                .statusCode(200) // HTTP Status Code should be 200
                .contentType(ContentType.JSON) // Response format should be "application/json"
                .body("data.email", equalTo("janet.weaver@reqres.in")  // "email" is "janet.weaver@reqres.in",
                        , "data.first_name", equalTo("Janet")  // "first_name" is "Janet"
                        , "data.last_name", equalTo("Weaver")  // "last_name" is "Weaver"
                        , "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }
}
