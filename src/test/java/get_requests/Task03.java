package get_requests;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Task03 extends ReqresBaseUrl {
     /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
    },
    "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
      */

    @Test
    public void test01() {

        // Set the url
        spec.pathParams("first", "unknown", "second", 3);


        // Set the Expected data
        ReqresTestData testData = new ReqresTestData();
        Map<String, Object> expectedData = testData.expectedDataMethod("true red", 2002, "#BF1932", "19-1664");
        Map<String, String> expectedSupport = testData.expectedSupportMethod("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");


        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);


        System.out.println(expectedSupport.get("url"));
        // Do Assertion
        Assert.assertEquals(expectedData.get("name"), ((Map) actualData.get("data")).get("name"));
        Assert.assertEquals(expectedData.get("year"), ((Map) actualData.get("data")).get("year"));
        Assert.assertEquals(expectedData.get("color"), ((Map) actualData.get("data")).get("color"));
        Assert.assertEquals(expectedData.get("pantone_value"), ((Map) actualData.get("data")).get("pantone_value"));
        Assert.assertEquals(expectedSupport.get("url"), ((Map) actualData.get("support")).get("url"));
        Assert.assertEquals(expectedSupport.get("text"), ((Map) actualData.get("support")).get("text"));


    }
}
