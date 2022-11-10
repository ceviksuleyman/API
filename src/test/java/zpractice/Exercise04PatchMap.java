package zpractice;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Exercise04PatchMap extends ReqresBaseUrl {

    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void e04Map() {

        spec.pathParams("1", "users", "2", 2);

        String jsonInString = "{\n" +
                "                \"name\": \"neo\"\n" +
                "               }";

        Map expectedData = ObjectMapperUtils.convertJsonToJava(jsonInString, Map.class);
        System.out.println("expectedData = " + expectedData);


        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{1}/{2}");
        response.prettyPrint();


        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);


        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.get("name"), actualData.get("name"));
    }
}
