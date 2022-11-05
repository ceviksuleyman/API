package delete_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Delete01 extends JsonplaceholderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        // set the url
        spec.pathParams("1", "todos", "2", "198");


        // set the expected data
        Map<String, String> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);


        // send the request and get the response
        Response response = given().spec(spec).when().delete("/{1}/{2}");
        //response.prettyPrint();


        // do assertion

        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);


        Assert.assertEquals(200, response.getStatusCode());
        // 1.yontem
        Assert.assertEquals(expectedData, actualData);
        // 2.yontem
        Assert.assertTrue(actualData.isEmpty());
    }
}
