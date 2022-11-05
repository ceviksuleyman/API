package post_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {
    /*
             Given
               1) https://jsonplaceholder.typicode.com/todos
               2) {
                     "userId": 55,
                     "title": "Tidy your room",
                     "completed": false
                   }
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
    public void post05ObjectMapper() throws IOException {

        // set the url
        spec.pathParam("1", "todos");

        // set the expected data
        String jsonInString = "{\n" +
                "                                        \"userId\": 55,\n" +
                "                                        \"title\": \"Tidy your room\",\n" +
                "                                        \"completed\": false,\n" +
                "                                        \"id\": 201\n" +
                "                                        }";
        Map<String, Object> expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData = " + expectedData);


        // send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();


        // do assertion

    }
}
