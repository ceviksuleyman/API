package get_requests;

import base_url.AutoExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Task01 extends AutoExerciseBaseUrl {

   /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */

    @Test
    public void task01() {

        //spec.pathParam("first", "productsList");
        spec.pathParams("first", "api", "second", "productsList");


        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();


        assertEquals(200, response.statusCode());  // HTTP Status Code should be 200
        System.out.println(response.statusCode());
        assertEquals("text/html; charset=utf-8", response.contentType()); // Content Type should be "text/html; charset=utf-8"
        System.out.println(response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());// Status Line should be HTTP/1.1 200 OK
        System.out.println(response.statusLine());

        //There must be 12 Women, 9 Men, 13 Kids usertype in products
        //Ürünlerde 12 Kadın, 9 Erkek, 13 Çocuk kullanıcı tipi bulunmalıdır.

        JsonPath json = response.jsonPath();
        //json.prettyPrint();


        List<String> categories = json.getList("products.category.usertype.usertype");
        List<String> category = json.getList("products.category.usertype.findAll{it.usertype}.usertype");
        System.out.println(category);
        System.out.println(categories);

        // 1.yontem
        int womenCount = 0;
        int menCount = 0;
        int kidsCount = 0;
        for (String w : categories) {
            switch (w) {
                case "Women":
                    womenCount++;
                    break;
                case "Men":
                    menCount++;
                    break;
                case "Kids":
                    kidsCount++;
                    break;
            }
        }
        System.out.println("women : " + womenCount);
        System.out.println("men : " + menCount);
        System.out.println("kids : " + kidsCount);
        assertEquals(12, womenCount);
        assertEquals(9, menCount);
        assertEquals(13, kidsCount);


        // 2. yontem
        List<String> women = json.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("women : " + women);
        List<String> men = json.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("men : " + men);
        List<String> kids = json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("kids : " + kids);

        assertEquals(12, women.size());
        assertEquals(9, men.size());
        assertEquals(13, kids.size());


        // 3.yontem
        assertEquals(12, categories.stream().filter(t -> t.equals("Women")).count());
        assertEquals(9, categories.stream().filter(t -> t.equals("Men")).count());
        assertEquals(13, categories.stream().filter(t -> t.equals("Kids")).count());


        // 4.yontem
        int countWomen = 0;
        int countMen = 0;
        int countKids = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals("Women")) countWomen++;
            if (categories.get(i).equals("Men")) countMen++;
            if (categories.get(i).equals("Kids")) countKids++;
        }
        assertEquals(12, countWomen);
        assertEquals(9, countMen);
        assertEquals(13, countKids);


        //List<String> deneme = json.getList("products.category.findAll{it.usertype}.usertype");
        //System.out.println(deneme);
        //System.out.println(deneme.size());
    }
}
