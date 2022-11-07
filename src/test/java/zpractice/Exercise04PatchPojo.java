package zpractice;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.reqresPojo.ReqresPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Exercise04PatchPojo  extends ReqresBaseUrl {

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
    public void e04Pojo() {

        spec.pathParams("1", "users", "2", 2);


        ReqresPojo expectedData = new ReqresPojo("neo");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{1}/{2}");
        response.prettyPrint();


        ReqresPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),ReqresPojo.class);
        System.out.println("actualData = " + actualData);
    }
}
