package get_requests.get02;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gorestPojo.GoRestDataPojo;
import pojos.gorestPojo.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13() {

        // Set the url
        spec.pathParams("1", "users", "2", "2508");


        // set the expected data
        GoRestDataPojo restDataPojo = new GoRestDataPojo(2508, "Sharmila Deshpande VM", "deshpande_sharmila_vm@becker.name", "female", "active");

        GoRestPojo expectedPojo = new GoRestPojo(null, restDataPojo); // payload
        System.out.println("expectedPojo = " + expectedPojo);


        // send the request and get the response
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();


        // do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class); //De-serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedPojo.getMeta(), actualData.getMeta());
        assertEquals(expectedPojo.getData().getId(), actualData.getData().getId());
        assertEquals(expectedPojo.getData().getName(), actualData.getData().getName());
        assertEquals(expectedPojo.getData().getEmail(), actualData.getData().getEmail());
        assertEquals(expectedPojo.getData().getGender(), actualData.getData().getGender());
        assertEquals(expectedPojo.getData().getStatus(), actualData.getData().getStatus());

    }
}
