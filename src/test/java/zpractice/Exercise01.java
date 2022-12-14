package zpractice;

import base_url.AutoExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Exercise01 extends AutoExerciseBaseUrl {

     /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
   */

    @Test
    public void e01() {

        String url = "https://automationexercise.com/api/brandsList";

        //spec.pathParams("1","api","2","brandsList");


        Response response = given().when().get(url);

        response.then().assertThat().
                statusCode(200).  //  HTTP Status Code should be 200
                contentType("text/html; charset=utf-8"). //  Content Type should be "text/html; charset=utf-8"
                statusLine("HTTP/1.1 200 OK"); //  Status Line should be HTTP/1.1 200 OK


        JsonPath json = response.jsonPath();
        json.prettyPrint();

        // groovy language  brands.findAll => brands body'nin tamamini aldik ,{it.brand=='Polo'} => body icerisinde brad esittir Polo olanlari al
        List<String> poloList = json.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("poloList = " + poloList);

        List<String> listHM = json.getList("brands.findAll{it.brand=='H&M'}.brand");
        System.out.println("listHM = " + listHM);

        System.out.println("poloList.size() = " + poloList.size());
        System.out.println("listHM.size() = " + listHM.size());

        System.out.println(json.getList("brands.findAll{it.brand=='Polo'}.brand"));

        Assert.assertNotEquals(poloList.size(), listHM.size());

        Assert.assertNotEquals(json.getList("brands.findAll{it.brand=='Polo'}.brand").size(), json.getList("brands.findAll{it.brand=='H&M'}.brand").size());
    }
}
