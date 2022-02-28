package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class Get08 extends JsonPlaceHolderBaseUrl {

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


    @Test
    public void get08(){

        spec.pathParams("first", "todos", "second", 2);

//      Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);

        System.out.println("expectedData = " + expectedData);

//      Send the request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//      If we are converting Json to Java --- this is DE-Serialization
//      If we are converting Java to Json --- this is Serialization

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }

    @Test
    public void test(){

//      Set the base URL
        spec.pathParams("first", "todos", "second", 2);

//      Set the expected data
        Todo expectedTodo = new Todo(1,2,"quis ut nam facilis et officia qui",false);

//      Send the Get request and Get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Todo actualTodo = response.as(Todo.class);
        System.out.println(actualTodo);

        assertEquals(expectedTodo.getId(), actualTodo.getId());
        assertEquals(expectedTodo.getTitle(), actualTodo.getTitle());
        assertEquals(expectedTodo.getUserId(), actualTodo.getUserId());
        assertEquals(expectedTodo.isCompleted(), actualTodo.isCompleted());
        System.out.println("Expected Todo: " +expectedTodo);
        System.out.println("Actual todo: "+actualTodo);
    }
}