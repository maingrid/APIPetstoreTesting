import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddNewPetTest extends BaseTest {

    @Test(description = "добавление нового питомца с корректными данными")
    public void addNewPetWithCorrectValues() {
        Integer id = 100;
        String name = "Demon";
        String status = "Available";

        Map<String, String> request = new HashMap<>();
        request.put("id", id.toString());
        request.put("name", name);
        request.put("status", status);

        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status));

    }

    @Test(description = "добавление нового питомца с именем состоящим из цифр")
    public void addNewPetWithUncorrectableName() {
        Integer id = 101;
        Integer name = 101;

        Map<String, Integer> request = new HashMap<>();
        request.put("id",id);
        request.put("name", name);

        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");

    }

    @Test(description = "добавление нового питомца с id состоящим из строки")
    public void addNewPetWithUncorrectableId() {
        String id = "102";
        String name = "Lunom";

        Map<String, String> request = new HashMap<>();
        request.put("id", id);
        request.put("name", name);

        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }
    //TODO сделать проверку на добавление со статусом unknown
}
