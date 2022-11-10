package get_requests.get02;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.dummyPojo.DummyRestApiDataPojo;
import pojos.dummyPojo.DummyRestApiResponsePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Get17 extends DummyBaseUrl {

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get17() {

        spec.pathParams("1", "employee", "2", 1);


        DummyRestApiDataPojo dataPojo = new DummyRestApiDataPojo("Tiger Nixon", 320800, 61, "");
        DummyRestApiResponsePojo expectedData = new DummyRestApiResponsePojo("success", dataPojo, "Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);


        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();


        DummyRestApiResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponsePojo.class);
        System.out.println("actualData = " + actualData);



        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getStatus(), actualData.getStatus());
        Assert.assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
        Assert.assertEquals(expectedData.getMessage(), actualData.getMessage());

    }
}
