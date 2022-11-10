package zpractice;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.reqresPojo.ReqresPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Exercise03PutPojo extends ReqresBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

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
    public void e03Pojo() {

        spec.pathParams("1", "users", "2", 2);

        ReqresPojo expectedData = new ReqresPojo("morpheus", "zion president");
        System.out.println("expectedData = " + expectedData);


        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{1}/{2}");
        response.prettyPrint();


        ReqresPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), ReqresPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getName(), actualData.getName());
        Assert.assertEquals(expectedData.getJob(), actualData.getJob());
    }
}
