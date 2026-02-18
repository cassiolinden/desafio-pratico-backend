import core.Config;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setBaseURI() {
        RestAssured.baseURI = Config.baseURI();
    }

    void setBasePath(String basePath){
        RestAssured.basePath = basePath;
    }
}
