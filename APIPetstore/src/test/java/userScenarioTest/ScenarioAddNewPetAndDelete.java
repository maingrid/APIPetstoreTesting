package userScenarioTest;

import BaseTest.BaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ScenarioAddNewPetAndDelete extends BaseTest {

    private final Integer id = 200;
    private final String name = "Eagle";
    private final String status = "Available";

    @Step
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
    @Step
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
    @Step
    @Test(description = "Удаление существующего питомца", priority = 3)
    public void delExistPet() {
        given().when()
                .delete(baseURI + "pet/{id}",id)
                .then()
                .time(lessThan(2000L))
                .log().all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(id.toString()));
    }
    @Step
    @Test(description = "поиск питомца после удаления", priority = 4)
    public void findPetByExistenceId() {
        given().when()
                .get(baseURI + "pet/{id}", id)
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
}
