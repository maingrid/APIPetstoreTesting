package BaseTest;

import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.baseURI;


public class BaseTest {

    @BeforeMethod
    public void setup(){
        System.out.println("-----Start test-----");
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @AfterMethod
    public void endTest() {
        System.out.println("-----End test-----");
    }

}
