package zpractice;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Exercise03PutMap extends ReqresBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void e03Map() {

        spec.pathParams("1", "users", "2", 2);

        String jsonInString = new ReqresTestData().expectedDataInStringReqres("morpheus", "zion president");
        Map expectedData = ObjectMapperUtils.convertJsonToJava(jsonInString, Map.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{1}/{2}");
        response.prettyPrint();


        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));
    }
}
