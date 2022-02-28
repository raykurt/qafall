package get_http_request_method;

import base_urls.HerokuAppBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class Get09 extends HerokuAppBaseUrl {

/*
        When  I send GET Request to https://restful-booker.herokuapp.com/booking/1
        Then  Response body should be like that;
   {
    "firstname": "Jim",
    "lastname": "Ericsson",
    "totalprice": 390,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2019-09-09",
        "checkout": "2021-09-24"
     }
}
*/

    @Test
    public void get09(){
//      We will do with de-serialization but you can try with MATCHERS and jsonpath

        spec.pathParams("first","booking","second",1);

        Map<String, Object> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin","2019-09-09");
        expectedBookingDates.put("checkout","2021-09-24");

        Map<String, Object> expectedData =new HashMap<>();
        expectedData.put("firstname","Jim");
        expectedData.put("lastname","Ericsson");
        expectedData.put("totalprice", 390);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates",expectedBookingDates);
        expectedData.put("additionalneeds","Breakfast");

        System.out.println("expectedData = " + expectedData);

//      Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = "+actualData);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("bookingdates"),actualData.get("bookingdates"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }

}
