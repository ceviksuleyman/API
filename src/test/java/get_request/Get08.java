package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class Get08 extends JsonplaceholderBaseUrl {

    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
    */

    /*
    De-Serialization : Json datayi Java objesine cevir
    Serialization : Java objesine Json formatina cevirme

    De-Serialization: Iki turlu yapacagiz.
    Gson: Google tarafindan uretilmistir
    Object Mapper:

     */
    @Test
    public void get08() {

        // Set the Url
        spec.pathParams("first", "todos", "second", 2);


        // Set the expected Data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println("expectedData : " + expectedData);


        //Send The Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData : " + actualData);
        //System.out.println(expectedData.get("userId"));

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());

    }
}