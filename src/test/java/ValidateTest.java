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
    Auth login = new Auth("admin", "password");
    // ----------
    Token validate = new Token();
    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;

    @BeforeEach
    void setup(){
        validate.setToken(login.getCookieToken(login));
        setBasePath("/auth/validate");
    }

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /validate - status code com token válido")
    void validarTokenValido(){
        response = request.post(validate);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /validate - token inválido")
    void validarTokenInvalido(){
        validate.setToken("B8hKXddVwPf2b2jT");
        response = request.post(validate);
        response.assertThat().statusCode(403);
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /validate - token vazio")
    void validarTokenVazio(){
        validate.setToken("");
        response = request.post(validate);
        response.assertThat().statusCode(403);
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /validate - token nulo")
    void validarTokenNulo(){
        validate.setToken(null);
        response = request.post(validate);
        response.assertThat().statusCode(403);
    }
}
