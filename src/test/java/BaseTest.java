import io.restassured.RestAssured;

public class BaseTest {
    void setBasePath(String basePath){
        RestAssured.baseURI = "https://automationintesting.online/api/";
        RestAssured.basePath = basePath;
        /*
        switch (basePath){
            case "/booking" -> RestAssured.port = 3000;
            case "/room" -> RestAssured.port = 3001;
            case "/branding" -> RestAssured.port = 3002;
            case "/auth/login" -> RestAssured.port = 3004;
            case "/auth/validate" -> RestAssured.port = 3004;
            case "/auth/logout" -> RestAssured.port = 3004;
            case "/report" -> RestAssured.port = 3005;
            case "/message" -> RestAssured.port = 3006;
        }
         */

        /*
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON)
                .build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
         */
    }
}
