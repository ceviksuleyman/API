package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get06 extends RestfulBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking/2325
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
       {
         "firstname": "Bradley",
         "lastname": "Pearson",
         "totalprice": 132,
         "depositpaid": false,
         "bookingdates": {                 // Outer json
             "checkin": "2022-10-27",      // inner json
             "checkout": "2022-11-07"      // inner json
         },
         "additionalneeds": "None"
       }

    */

    @Test
    public void get06() {

        // 1. Set the Url (Url olustur)
        spec.pathParams("first", "booking", "second", 2325);

        // 2. Set the expected data (put, post, patch)

        // 3. Send The Request And Get the Response(Talep gonder cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();



        // 4. Do Assertion

        // 1.yontem HardAssert
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Bradley"))
                .body("lastname", equalTo("Pearson"))
                .body("totalprice", equalTo(132))
                .body("depositpaid", equalTo(false))
                .body("bookingdates.checkin", equalTo("2022-10-27")) // inner json
                .body("bookingdates.checkout", equalTo("2022-11-07")) // inner json
                .body("additionalneeds", equalTo("None"));

        // 2. softAssert
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Bradley")
                        , "lastname", equalTo("Pearson")
                        , "totalprice", equalTo(132)
                        , "depositpaid", equalTo(false)
                        , "bookingdates.checkin", equalTo("2022-10-27")
                        , "bookingdates.checkout", equalTo("2022-11-07")
                        , "additionalneeds", equalTo("None"));


        // Jsonpath class'inin kullanimi
        JsonPath json = response.jsonPath();
        assertEquals("Bradley", json.getString("firstname"));
        assertEquals("Pearson", json.getString("lastname"));
        assertEquals(132, json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27", json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07", json.getString("bookingdates.checkout"));
        assertEquals("None", json.getString("additionalneeds"));


        // softAssert class  3 adimda kullanilir
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getString("firstname"), "Bradley", "Firsname hatali");
        softAssert.assertEquals(json.getString("lastname"), "Pearson", "Lastname hatali");
        softAssert.assertEquals(json.getInt("totalprice"), 132, "totalprice hatali");
        softAssert.assertFalse(json.getBoolean("depositpaid"), "totalprice hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2022-10-27");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2022-11-07");
        softAssert.assertEquals(json.getString("additionalneeds"), "None");

        softAssert.assertAll(); // softAssert ile dogrulama islemleri sonun tum yaptigimiz dogrulamalari kontrol etmek icin kullandik.
        // Eger assertAll() kullanmazsak testimizi hatali bile olsa passed olur

    }
}
