package post_http_request_method;

import base_urls.MedunnaBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Registrant;

import static io.restassured.RestAssured.given;
import static utility.WriteToTxt.generateData;

public class Post04 extends MedunnaBaseUrl {

//      https://medunna.com/api/register

    @Test
    public void testPost(){

//      Set the base URL
        spec.pathParams("first","api","second","register");

        Faker faker = new Faker();

        Registrant registrant = new Registrant();

        registrant.setFirstName(faker.name().firstName());
        registrant.setLastName(faker.name().lastName());
        registrant.setEmail(faker.internet().emailAddress());
        registrant.setLangKey("tr");
        registrant.setLogin(registrant.getFirstName()+"1");
        registrant.setPassword(faker.internet().password(8,20,true,true));
        registrant.setSsn(faker.idNumber().ssnValid());

//      Send the POST Request and GET the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(registrant).when().post("/{first}/{second}");

        response.then().statusCode(201);

        String fileName = "C:/Users/rayku/IdeaProjects/qafallapi2022/src/test/java/test_data/medunna_registrant_info.txt";

        generateData(registrant,fileName);

    }
}
