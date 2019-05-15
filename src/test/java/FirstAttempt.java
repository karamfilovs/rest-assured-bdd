import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class FirstAttempt {
    static {
        RestAssured.baseURI = "https://st2016.inv.bg";
        RestAssured.basePath = "/RESTapi";
        RestAssured.authentication = RestAssured.preemptive().basic("karamfilovs@gmail.com", "123456");
    }

    @Test
    public void test(){
        Response response = RestAssured.given().log().all().contentType(ContentType.JSON).when().get("/invoices");
        System.out.println(response.asString());

    }

}
