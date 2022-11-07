package get_requests.get01;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrl {
   /*
   Given
       https://jsonplaceholder.typicode.com/todos
   When
       I send a GET request to the Url
   And
       Accept type is "application/json"
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       There should be 200 todos
   And
       "quis eius est sint explicabo" should be one of the todos title
   And
       2, 7, and 9 should be among the userIds
   */

    @Test
    public void get04() {
        // 1.Set the Url
        spec.pathParam("first", "todos"); // tek parametre oldugu icin pathParam()

        // 2.Set the expected Data


        // 3.Send The request And get The Response
        Response response = given().spec(spec).when()
                .accept(ContentType.JSON) //  -> Accept type is "application/json"
                .get("/{first}");


        response.prettyPrint();


        // 4. Do Assertion
        response.then().assertThat()
                .statusCode(200)  // -> HTTP Status Code should be 200
                .contentType(ContentType.JSON) // -> Response format should be "application/json"
                .body("id", hasSize(200), // -> There should be 200 todos
                        "title", hasItem("quis eius est sint explicabo"),  // -> "quis eius est sint explicabo" should be one of the todos title
                        "userId", hasItems(2, 7, 9)); // -> 2, 7, and 9 should be among the userIds

    }
}
