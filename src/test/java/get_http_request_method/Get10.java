package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get10 extends JsonPlaceHolderBaseUrl {
/*
        Given  https://jsonplaceholder.typicode.com/todos/2
        When I send a Get Request
        Then the actual data should be as following;
        {
        "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
        }
*/
    // 1 way is Matchers
    // 2 way is JsonPath
    // 2 way is De-Serialization

    @Test
    public void get01(){
        // Set the url
        spec.pathParams("first","todos","second",2);
        // Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        // Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 1.way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId",equalTo(1)).
                body("id",equalTo(2)).
                body("title",equalTo("quis ut nam facilis et officia qui")).
                body("completed",equalTo(false));

        // 2.way jsonpath
        JsonPath json = response.jsonPath();
        assertEquals(1,json.getInt("userId"));
        assertEquals(2,json.getInt("id"));
        assertEquals("quis ut nam facilis et officia qui",json.getString("title"));
        assertEquals(false,json.getBoolean("completed"));

        // 3. way
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }
}