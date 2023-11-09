import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class DeletePetTest extends BaseTest{

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

}
