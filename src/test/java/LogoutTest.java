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
    Auth session = new Auth("admin", "password");
    Token sessionToken = new Token();
    static String firstToken = "";

    @BeforeEach
    void setup(){
        sessionToken.setToken(session.getCookieToken(session));
        setBasePath("/auth/logout");
    }
    // ----------

    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /logout - status code com token válido")
    void validarLogout(){
        firstToken = sessionToken.getToken();
        response = request.post(sessionToken);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /logout - reutilização de token")
    void validarLogoutComTokenJaUsado(){
        sessionToken.setToken(firstToken);
        response = request.post(sessionToken);
        response.assertThat().statusCode(404);
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /logout - token inválido")
    void validarLogoutComTokenInvalido(){
        sessionToken.setToken("token-invalido");
        response = request.post(sessionToken);
        response.assertThat().statusCode(404);
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /logout - token vazio")
    void validarLogoutComTokenVazio() {
        sessionToken.setToken(null);
        response = request.post(sessionToken);
        response.assertThat().statusCode(404);
    }
}
