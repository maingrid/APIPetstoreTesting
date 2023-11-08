import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.baseURI;


public class BaseTest {

    @BeforeMethod
    public void setup(){

        baseURI = "https://petstore.swagger.io/v2/";
    }

}
