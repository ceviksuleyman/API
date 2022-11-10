package get_requests.get02;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get16 extends DummyBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*

    Given
        URL: https://dummy.restapiexample.com/api/v1/employees
    When
        User send the request
    Then
        i) Status code is 200
    And
        ii) There are 24 employees
    And
        iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        iv) The greatest age is 66
    And
        v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
        vi) Total salary of all employees is 6,644,770
     */
    @Test
    public void get16() {

        spec.pathParam("1", "employees");

        Response response = given().spec(spec).when().get("/{1}");

        JsonPath json = response.jsonPath();
        //json.prettyPrint();


        // There are 24 employees
        // "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().body("data", hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        List<String> data = json.getList("data.findAll{it.employee_name}.employee_name");
        //List<String> data2 = json.getList("data.employee_name");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(24, data.size());


        // The greatest age is 66
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("sorted ages = " + ages);
        int greatestAge = ages.get(ages.size() - 1);
        Assert.assertEquals(66, greatestAge);


        // The name of the lowest age is "Tatyana Fitzpatrick"
        String lowestAgeEmployee = json.getString("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("lowestAgeEmployee = " + lowestAgeEmployee);
        Assert.assertEquals("[Tatyana Fitzpatrick]", lowestAgeEmployee);


        //Total salary of all employees is 6,644,770
        List<Integer> salaryList = json.getList("data.findAll{it.employee_name}.employee_salary");

        int salarySum = salaryList.stream().reduce(Integer::sum).get();
        int salarySum2 = salaryList.stream().reduce(Math::addExact).get();
        System.out.println("salarySum = " + salarySum);
        System.out.println("salarySum2 = " + salarySum2);

        Assert.assertEquals(6644770, salarySum);

    }
}
