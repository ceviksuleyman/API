package delete_requests;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.dummyPojo.DummyRestApiDeletePojo;
import pojos.dummyPojo.DummyRestApiResponsePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Delete02 extends DummyBaseUrl {

    /*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
       */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/delete/2
    When
       User send the request and get response
    Then
        Status code is 200
    And
        "status" is "success"
    And
        "data" is "2"
    And
        "message" is "Successfully! Record has been deleted"
     */

    @Test
    public void delete02() {

        spec.pathParams("1", "delete", "2", 2);


        DummyRestApiDeletePojo expectedData = new DummyRestApiDeletePojo("success", "2", "Successfully! Record has been deleted");
        System.out.println("expectedData = " + expectedData);


        Response response = given().spec(spec).delete("/{1}/{2}");
        response.prettyPrint();


        DummyRestApiDeletePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiDeletePojo.class);
        System.out.println("actualData = " + actualData);


        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getStatus(), actualData.getStatus());
        Assert.assertEquals(expectedData.getData(), actualData.getData());
        Assert.assertEquals(expectedData.getMessage(), actualData.getMessage());

    }
}
