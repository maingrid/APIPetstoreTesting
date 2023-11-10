package userScenarioTest;

import BaseTest.BaseTest;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class ScenarioAddNewPetAndChangeName extends BaseTest {

    private final Integer id = 150;
    private final String name = "Monstr Ghost";
    private final String status = "Available";
    private final String changeName = "Ghost Busters";

    @Test(description = "добавление нового питомца с корректными данными", priority = 1)
    public void addNewPetWithCorrectableValues() {
        Map<String,String> request = new HashMap<>();
        request.put("id", id.toString());
        request.put("name", name);
        request.put("status", status);

        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(3000L))
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name",equalTo(name))
                .body("status", equalTo(status));
    }

    @Test(description = "просмотр питомца после добавления", priority = 2)
    public void viewingPetAfterAdded() {
        given().when()
                .get(baseURI + "pet/{id}", id)
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status));
    }

    @Test(description = "изменение имени у добавленого питомца", priority = 3)
    public void changeNameAtAddedPet() {
        Map<String,String> request = new HashMap<>();
        request.put("id", id.toString());
        request.put("name", changeName);
        request.put("status", status);

        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "pet/")
                .then()
                .log().all()
                .time(lessThan(3000L))
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name",equalTo(changeName))
                .body("status", equalTo(status));
    }

    @Test(description = "просмотр питомца после изменения имени", priority = 4)
    public void viewingPetAfterChangedName() {
        given().when()
                .get(baseURI + "pet/{id}", id)
                .then()
                .log().all()
                .time(lessThan(2000L))
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .body("id", equalTo(id))
                .body("name", equalTo(changeName))
                .body("status", equalTo(status));
    }
}
