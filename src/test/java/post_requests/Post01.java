package post_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import TestData.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post01 extends JsonplaceholderBaseUrl {
    /*
      Given
        1)  https://jsonplaceholder.typicode.com/todos
        2)  {
              "userId": 55,
              "title": "Tidy your room",
              "completed": false
             }
     When
      I send POST Request to the Url
     Then
         Status code is 201
     And
         response body is like {
                                 "userId": 55,
                                 "title": "Tidy your room",
                                 "completed": false,
                                 "id": 201
                                 }
  */
    @Test
    public void post01() {

        // Set the url
        spec.pathParam("first", "todos");

        // Set the expected data
        JsonPlaceHolderTestData testData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = testData.expectedDataMethod(55, "Tidy your room", false);


        // Send the Request and Get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();


        // Do Assertion
        Assert.assertEquals(201,response.statusCode());

        Map<String, Object> actualData = response.as(HashMap.class); // De-serialization ->  Gson  json'dan javaya


        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
}
