import core.ApiRequestHelper;
import entities.auth.Auth;
import entities.auth.Token;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Booker Platform Admin")
@Feature("Logout")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest extends BaseTest {
    // Pré-condição
    Auth session = new Auth("admin", "password");
    HashMap<String, Object> loginBody = new HashMap<>();
    Token sessionToken = new Token();
    static String firstToken = "";

    @BeforeEach
    void getTokenFromLogin(){
        loginBody.put("username", session.getUsername());
        loginBody.put("password", session.getPassword());
        sessionToken.setToken(session.getCookieToken(loginBody));
    }
    // ----------

    ApiRequestHelper request = new ApiRequestHelper();
    HashMap<String, Object> body = new HashMap<>();
    String endpoint = "/auth/logout";
    static Response response;

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /logout - status code com token válido")
    void validarLogout(){
        firstToken = sessionToken.getToken();
        body.put("token", firstToken);
        response = request.post(endpoint, body);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /logout - reutilização de token")
    void validarLogoutComTokenJaUsado(){
        body.put("token", firstToken);
        response = request.post(endpoint, body);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /logout - token inválido")
    void validarLogoutComTokenInvalido(){
        body.put("token", "token-invalido");
        response = request.post(endpoint, body);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /logout - token vazio")
    void validarLogoutComTokenVazio() {
        body.put("token", null);
        response = request.post(endpoint, body);
        assertEquals(404, response.getStatusCode());
    }
}
