import core.ApiRequestHelper;
import entities.auth.Auth;
import entities.auth.Token;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

@Epic("Booker Platform Admin")
@Feature("Validate")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidateTest extends BaseTest {
    // Pré-condição
    Auth auth = new Auth("admin", "password");
    Token token;
    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;

    @BeforeEach
    void setup(){
        token = new Token(auth.getToken(auth));
        setBasePath("/auth/validate");
    }

    // ----------

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /validate - status code com token válido")
    void validarTokenValido(){
        response = request.post(token);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /validate - token inválido")
    void validarTokenInvalido(){
        token.setToken("B8hKXddVwPf2b2jT");
        response = request.post(token);
        response.assertThat().statusCode(403);
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /validate - token vazio")
    void validarTokenVazio(){
        token.setToken("");
        response = request.post(token);
        response.assertThat().statusCode(401);
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /validate - token nulo")
    void validarTokenNulo(){
        token.setToken(null);
        response = request.post(token);
        response.assertThat().statusCode(401);
    }
}
