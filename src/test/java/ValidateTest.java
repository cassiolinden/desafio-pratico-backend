import core.ApiRequestHelper;
import entities.auth.Auth;
import entities.auth.Token;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Booker Platform Admin")
@Feature("Validate")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidateTest extends BaseTest {
    Auth login = new Auth("admin", "password");
    HashMap<String, Object> loginBody = new HashMap<>();
    Token validate = new Token();
    ApiRequestHelper request = new ApiRequestHelper();
    HashMap<String, Object> body = new HashMap<>();
    String endpoint = "/auth/validate";
    static Response response;

    @BeforeEach
    void getTokenFromLogin(){
        loginBody.put("username", login.getUsername());
        loginBody.put("password", login.getPassword());
        validate.setToken(login.getCookieToken(loginBody));
    }

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /validate - status code com token válido")
    void validarTokenValido(){
        body.put("token", validate.getToken());
        response = request.post(endpoint, body);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /validate - token inválido")
    void validarTokenInvalido(){
        body.put("token", "B8hKXddVwPf2b2jT");
        response = request.post(endpoint, body);
        assertEquals(403, response.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /validate - token vazio")
    void validarTokenVazio(){
        body.put("token", "");
        response = request.post(endpoint, body);
        assertEquals(403, response.getStatusCode());
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /validate - token nulo")
    void validarTokenNulo(){
        body.put("token", null);
        response = request.post(endpoint, body);
        assertEquals(403, response.getStatusCode());
    }
}
