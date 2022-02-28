package get_http_request_method;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Get01 extends HerokuAppBaseUrl {

/*
        Given https://restful-booker.herokuapp.com/booking/3
        When user sends a GET request to the url
        Then HTTP status code should be 200
        And   content type should be JSON
        And status line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01(){

//        1. Set the URL
        String endpoint = "https://restful-booker.herokuapp.com/booking/3";
//        This is primitive way that we do not prefer!!!

//        2. Set the expected data

//        3. Send the Request and Get the Response
        Response response = given().when().get(endpoint);
        response.prettyPrint();

//        4. Do the validation and assertion

    }

    @Test
    public void test(){
//        1. Set the base URL

        spec.pathParams("firstPar","booking", "secPar",3);

//        2. Set the expected data

//        3. Send the Get Request and Get the Response
        Response response = given().spec(spec).when().get("/{firstPar}/{secPar}");
        response.prettyPrint();

//        4. Do the validation
        response.then().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON);
        System.out.println(response.headers());
    }
}
