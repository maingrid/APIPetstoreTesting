package validationsPetTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import BaseTest.BaseTest;

public class DeletePetTest extends BaseTest {

    @Test(description = "Удаление существующего питомца")
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

    @Test(description = "удаление несуществующего питомца")
    public void delNonExistPet() {
        given().when()
                .delete(baseURI + "pet/")
                .then()
                .time(lessThan(2000L))
                .log().all()
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }

    @Test(description = "удаление питомца передавая некоректный id")
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
