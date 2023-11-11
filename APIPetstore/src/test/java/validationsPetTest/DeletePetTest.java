package validationsPetTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import BaseTest.BaseTest;

public class DeletePetTest extends BaseTest {

    @Test(description = "Removing an existing pet")
    public void delExistPet() {
        Integer id = 100;
        given().when()
                .delete(baseURI + "pet/{id}",id)
                .then()
                .time(lessThan(2000L))
                .log().all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("100"));
    }

    @Test(description = "deleting a non-existent pet")
    public void delNonExistPet() {
        given().when()
                .delete(baseURI + "pet/")
                .then()
                .time(lessThan(2000L))
                .log().all()
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }

    @Test(description = "deleting a pet by passing an incorrect id")
    public void delPetWithUncorrectableId() {
        given().when()
                .delete(baseURI + "pet/{id}", "test")
                .then()
                .time(lessThan(2000L))
                .log().all()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body("code",equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("java.lang.NumberFormatException: For input string: \"test\""));

    }

}
