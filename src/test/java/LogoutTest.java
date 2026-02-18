import core.ApiRequestHelper;
import entities.auth.Auth;
import entities.auth.Token;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

@Epic("Booker Platform Admin")
@Feature("Logout")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest extends BaseTest {
    // Pré-condição
    Auth auth = new Auth("admin", "password");
    Token token;
    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;
    static String firstToken = "";

    @BeforeEach
    void setup(){
        token = new Token(auth.getToken(auth));
        setBasePath("/auth/logout");
    }
    // ----------

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /logout - status code com token válido")
    void validarLogout(){
        firstToken = token.getToken();
        response = request.post(token);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /logout - reutilização de token")
    void validarLogoutComTokenJaUsado(){
        token.setToken(firstToken);
        response = request.post(token);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /logout - token inválido")
    void validarLogoutComTokenInvalido(){
        token.setToken("token-invalido");
        response = request.post(token);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /logout - token vazio")
    void validarLogoutComTokenVazio() {
        token.setToken(null);
        response = request.post(token);
        response.assertThat().statusCode(400);
    }
}
