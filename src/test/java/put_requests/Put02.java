package put_requests;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.dummyPojo.DummyRestApiDataPojo;
import pojos.dummyPojo.DummyRestApiResponsePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Put02 extends DummyBaseUrl {

    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

   /*
  Given
     URL: https://dummy.restapiexample.com/api/v1/update/21
               {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }
  When
      User sends Put request

  Then
       i) Status code is 200

   And
       ii) Response body should be like the following

                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
      */


    @Test
    public void put02() {

        spec.pathParams("1", "update", "2", 21);

        DummyRestApiDataPojo putData =
                new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");

        DummyRestApiResponsePojo expectedData = new DummyRestApiResponsePojo("success", putData, "Successfully! Record has been updated.");
        System.out.println("expectedData = " + expectedData);


        Response response = given().spec(spec).contentType(ContentType.JSON).body(putData).when().put("/{1}/{2}");
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
