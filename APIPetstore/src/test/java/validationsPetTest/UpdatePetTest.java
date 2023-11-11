package validationsPetTest;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import BaseTest.BaseTest;

public class UpdatePetTest extends BaseTest {

    @Test(description = "changing the name of the pet that was added")
    public void updateExistPetName() {
        Integer id = 50;
        String changeName = "Luna";

        Map<String, String> request = new HashMap<>();
        request.put("id", id.toString());
        request.put("name", changeName);

        given().contentType("application/json")
                .body(request)
                .when()
                .put(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("id", equalTo(id))
                .body("name", equalTo(changeName));
    }

    @Test(description = "changing the name of a pet that doesn't exist")
    public void updateNonExistPetName() {
        Integer id = 234823482;
        String changeName = "null";

        Map<String, String> request = new HashMap<>();
        request.put("id", id.toString());
        request.put("name", changeName);

        given().contentType("application/json")
                .body(request)
                .when()
                .put(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body("code", equalTo(1))
                .body("message", equalTo("Pet not found"))
                .body("type", equalTo("error"));
    }

    @Test(description = "changing the pet without transferring the name")
    public void updatePetWithoutName() {
        Integer id = 50;

        Map<String,String> request = new HashMap<>();
        request.put("id", id.toString());

        given().contentType("application/json")
                .body(request)
                .when()
                .put(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }

}
