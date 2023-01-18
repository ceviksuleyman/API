package zpractice;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import TestData.ReqresTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Exercise02PostMap extends ReqresBaseUrl {

    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void e02Map() {

        // set the url
        spec.pathParam("1", "users");

        // set the expected data

        //String jsonInString2 = "{\n" +
        //        "                \"name\": \"morpheus\",\n" +
        //        "                \"job\": \"leader\"\n" +
        //        "                }";


        //String jsonInString = new ReqresTestData().expectedDataInStringReqres("morpheus", "leader");
        String jsonInString = new ReqresTestData().expectedDataInStringReqres("morpheus", "leader");
        Map<String, String> expectedData = ObjectMapperUtils.convertJsonToJava(jsonInString, Map.class);

        System.out.println("expectedData = " + expectedData);


        // send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();


        // do assertion
        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals(expectedData.get("name"), actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"), actualData.get("job"));

    }
}
