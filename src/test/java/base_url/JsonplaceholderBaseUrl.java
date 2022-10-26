package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {

    protected RequestSpecification spec;

    @Before //extend yaptigimiz her class'da her testten once calismasi icin
    public void setUp() {

        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();
    }
}
