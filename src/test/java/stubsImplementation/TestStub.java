package stubsImplementation;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class TestStub {
    //1- Start the server
    //2- create stub
    //3- test it using rest assured
    //4- close the server
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();
    @Before
    public void setup(){
        wireMockRule.start();
        createStub();
    }
    @After
    public void tearDown(){
        wireMockRule.stop();
    }
    public void createStub(){
        stubFor(get(urlEqualTo("/testEndpoint"))
                .willReturn(aResponse().withStatus(200)
                .withBody("{\"message\":\"Hello! This is a mock response\"}")
                .withHeader("Content-Type", "application/json")));
    }

    @Test
    public void test(){
        RestAssured.baseURI = "http://localhost:8080";
        Response response = given()
                .get("/testEndpoint");
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
    }
}
