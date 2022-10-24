import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1- Postman ,manuel API testleri icin kullandik
    2- Otomasyon testleri icinde Rest Assured Library kullanacagiz.
    3- Otomasyon testlerimizi yaparken asagidaki adimlari izleriz.
       a) Gereksinimleri anlamak,
       b) Test Case yaziyoruz

          i) Test Case yaziminda "Gherkin Language" kullanacagiz. Yazilim diline hakim olsak da, karsimizdaki
          kisiler hakim olmayabilir ama Gherkin ile yazilan testleri anlamakta zorluk cekmeyeceklerdir.
          Gherkin dilinde kullanacagimiz keywordler;
          - Given : On Kosullar
          - When  : Yapilacak aksiyonlar(get(), puy(), post(), patch() ve delete())
          - Then  : Istek yaptiktan sonra (request gonderdikten sonra) dogrulama
          - And   : Coklu islemlerde kullanacagiz

       c) Test kodlarimizi yazmaya baslayacagiz

          i) Set the URL,
          ii) Set the expected Data(beklenen datanin olusturulmasi; Post ,Put ,Patch)
          iii) Type code to send request(Talep gondermek icin kod yazimi)
          iv) Do Assertion(dogrulama yapma)
     */

    /*
    Given
        https://restful-booker.herokuapp.com/booking/101
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
    public void get01() {
        //i) Set the URL,
        String url = "https://restful-booker.herokuapp.com/booking/101";


        //ii) Set the expected Data(beklenen datanin olusturulmasi; Post ,Put ,Patch)
        // Bizden post,put yada patch istenmedigi icin bu case'de kullanmayacagiz.


        //iii) Type code to send request(Talep gondermek icin kod yazimi)
        Response response = given().when().get(url);
        response.prettyPrint();


        //iv) Do Assertion(dogrulama yapma)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

    }
}
