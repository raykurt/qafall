package get_http_request_method;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get05 extends HerokuAppBaseUrl {

/*
           Given  https://restful-booker.herokuapp.com/booking
           When  User send a request to the URL
           Then  Status code is 200
           And  Among the data there should be someone whose firstname is "Mark" and last name is "Brown"
*/

    @Test
    public void get05(){

        spec.pathParam("first","booking").queryParams("firstname","Mark","lastname","Brown");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
    }
}
