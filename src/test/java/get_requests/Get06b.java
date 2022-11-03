package get_requests;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresBaseUrl {

    /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then
        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3  -> id 3 ten az olanlari yazdir
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2  -> id 3 ten az olanlari 2 tane oldugunu dogrula
   */

    @Test
    public void get06b() {

        spec.pathParam("first", "unknown");


        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().body("data", hasSize(6)); //dataya karsilik gelen listin icerisinde 6 tane eleman var kullanilabilmesi icin list olmasi gereklidir.


        JsonPath json = response.jsonPath();

        // 1)Status code is 200
        assertEquals(200, response.statusCode());

        // 2)Print all pantone_values
        System.out.println(json.getList("data.pantone_value"));

        //3)
        // Print all ids greater than 3 on the console -> Konsolda 3'ten büyük tüm kimlikleri yazdır
        List<Integer> ids = json.getList("data.findAll{it.id>3}.id");
        System.out.println(ids);
        ids.forEach(System.out::println);

        // Assert that there are 3 ids greater than 3  -> 3'ten büyük 3 kimlik olduğunu iddia edin
        assertEquals(3, ids.size());


        //4)
        // Print all names whose ids are less than 3 on the console -> Kimlikleri 3'ten küçük olan tüm adları konsolda yazdırın
        List<String> names = json.getList("data.findAll{it.id<3}.name"); // data.findAll{it.id<3}  Gelen datadan id'si 3 den kucuk olanlari bul .name, name degerlerini getir
        System.out.println(names);


        // Assert that the number of names whose ids are less than 3 is 2  -> Kimlikleri 3'ten küçük olan isimlerin sayısının 2 olduğunu iddia edin.
        assertEquals(2, names.size());
    }
}
