package get_requests.get02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01b {

    /*
   Given
       https://reqres.in/api/users/3
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
   */

    @Test
    public void get01b() {

        //1. Set The Url
        String url = "https://reqres.in/api/users/3";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)  //   HTTP Status Code should be 200
                .contentType(ContentType.JSON) //  Content Type should be JSON
                .statusLine("HTTP/1.1 200 OK");  //  Status Line should be HTTP/1.1 200 OK


        System.out.println("Status Code : " + response.statusCode());
        System.out.println("ContentType : " + response.contentType());
        System.out.println("Status Line : " + response.statusLine());
        System.out.println("Server : " + response.getHeader("Server"));

    }
}
