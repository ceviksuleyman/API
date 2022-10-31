package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

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
    public void get10() {

        //Set the Url
        spec.pathParams("first", "users", "second", "2986");

        // Set the expected Data
        //Map<String, String> dataMap = new HashMap<>();
        //dataMap.put("name", "Navin Talwar");
        //dataMap.put("email", "navin_talwar@mclaughlin.name");
        //dataMap.put("gender", "male");
        //dataMap.put("status", "inactive");
        //Map<String, Object> expectedData = new HashMap<>();
        //expectedData.put("meta", null);
        //expectedData.put("data", dataMap);

        //System.out.println(expectedData);

        GoRestTestData goRestTestData = new GoRestTestData();

        // direkt inner map'a data girdik
        Map<String, Object> expectedTestData =
                goRestTestData.expectedDataMethod(null, goRestTestData.dataKeyMap(
                        "Navin Talwar",
                        "navin_talwar@mclaughlin.name",
                        "male",
                        "inactive"));

        //=====================================================================

        //once inner map'i olusturduk
        Map<String, String> dataKeyMap = goRestTestData.dataKeyMap(
                "Navin Talwar",
                "navin_talwar@mclaughlin.name",
                "male",
                "inactive");
        // expextedDataya inner mapi girdik
        Map<String, Object> expectedTestData2 =
                goRestTestData.expectedDataMethod(null, dataKeyMap);
        System.out.println("expected Data : " + expectedTestData2);


        // Send The Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actual Data : " + actualData);


        //ilk Assert yontem
        assertEquals(expectedTestData.get("meta"), actualData.get("meta"));
        assertEquals(((Map<?, ?>) expectedTestData.get("data")).get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(((Map<?, ?>) expectedTestData.get("data")).get("email"), ((Map) actualData.get("data")).get("email"));
        assertEquals(((Map<?, ?>) expectedTestData.get("data")).get("gender"), ((Map) actualData.get("data")).get("gender"));
        assertEquals(((Map<?, ?>) expectedTestData.get("data")).get("status"), ((Map) actualData.get("data")).get("status"));


        //ikinci yontem
        assertEquals(expectedTestData2.get("meta"), actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map) actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) actualData.get("data")).get("status"));
        assertEquals(200, response.statusCode());

    }
}
