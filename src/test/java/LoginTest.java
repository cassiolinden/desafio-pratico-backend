import core.ApiRequestHelper;
import entities.auth.Auth;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

@Epic("Booker Platform Admin")
@Feature("Login")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {
    static Auth login = new Auth("admin", "password");
    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;

    @BeforeEach
    void setup(){
        setBasePath("/auth/login");
    }

    @Test
    @Order(1)
    @DisplayName("Validar requisição POST /login - status code")
    void validarLoginStatusCode(){
        response = request.post(login);
        response.assertThat().statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição POST /login - senha inválida")
    void validarSenhaInvalida(){
        login.setPassword("senhainvalida");
        response = request.post(login);
        response.assertThat().statusCode(403);
    }

    @Test
    @Order(3)
    @DisplayName("Validar requisição POST /login - nome de usuário inválido")
    void validarUsuarioInvalido(){
        login.setUsername("grandefera");
        response = request.post(login);
        response.assertThat().statusCode(403);
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição POST /login - senha ausente")
    void validarSenhaAusente(){
        login.setPassword(null);
        response = request.post(login);
        response.assertThat().statusCode(403);
    }

    @Test
    @Order(5)
    @DisplayName("Validar requisição POST /login - credenciais vazias")
    void validarCredenciaisVazias(){
        response = request.post();
        response.assertThat().statusCode(400);
    }

}
