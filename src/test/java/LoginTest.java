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
@Feature("Login")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {
    static Auth login = new Auth("admin", "password");
    ApiRequestHelper request = new ApiRequestHelper();
    static HashMap<String, Object> body = new HashMap<>();
    String endpoint = "/auth/login";
    static Response response;

    @BeforeEach
    void putBody(){
        body.put("username", login.getUsername());
        body.put("password", login.getPassword());
    }

    @Test
    @Order(5)
    @DisplayName("Validar requisição POST /login - status code")
    void validarLoginStatusCode(){
        response = request.post(endpoint, body);
        assertEquals(200, response.statusCode());
    }

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /login - senha inválida")
    void validarSenhaInvalida(){
        body.put("password", "senhainvalida");
        response = request.post(endpoint, body);
        assertEquals(403, response.statusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /login - nome de usuário inválido")
    void validarUsuarioInvalido(){
        body.put("username", "grandefera");
        response = request.post(endpoint, body);
        assertEquals(403, response.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /login - senha ausente")
    void validarSenhaAusente(){
        body.put("password", null);
        response = request.post(endpoint, body);
        assertEquals(403, response.getStatusCode());
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /login - credenciais vazias")
    void validarCredenciaisVazias(){
        response = request.post(endpoint);
        assertEquals(400, response.getStatusCode());
    }

}
