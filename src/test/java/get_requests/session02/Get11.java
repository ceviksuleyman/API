package get_requests.session02;

import base_url.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void get11() {

        //1. Set The URL
        spec.pathParam("first", "users");

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Prasad Butt", "Girish Gill II", "Sarvin Bhattacharya"));


        // 1.yontem  Kadın kullanıcılar erkek kullanıcılara eşit veya daha az
        JsonPath json = response.jsonPath();
        List<String> gender = json.getList("data.gender");
        System.out.println("genders -> " + gender);

        int maleCount = 0;
        int femaleCount = 0;
        for (String w : gender) {

            if (w.equals("male")) maleCount++;
            if (w.equals("female")) femaleCount++;
        }
        System.out.println("countMale = " + maleCount);
        System.out.println("countFemale = " + femaleCount);
        Assert.assertTrue(femaleCount <= gender.size() - femaleCount);


        //2.yontem
        List<String> femaleNames = response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        System.out.println("femaleNames = " + femaleNames);

        List<String> maleNames = response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println("maleNames = " + maleNames);

        Assert.assertTrue(femaleNames.size() <= maleNames.size());
    }
}
