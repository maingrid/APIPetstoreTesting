import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FindPetTest extends BaseTest {

    @Test(description = "поиск питомца по id")
    public void findPetById() {
        given().when()
                .get(baseURI + "pet/{id}", 2)
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .body("id", equalTo(2))
                .body("name", notNullValue());
    }


    @Test(description = "поиск питомца по несуществующему id")
    public void findPetByExistenceId() {
        given().when()
                .get(baseURI + "pet/{id}", 43563453)
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .contentType("application/json")
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Pet not found"));
    }


    @Test(description = "поиск питомца по некорректному id")
    public void findPetByUncorrectableId() {
        given().when()
                .get(baseURI + "pet/{id}", "abc")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .contentType("application/json")
                .body("code", equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("java.lang.NumberFormatException: For input string: \"abc\""));

    }
}
