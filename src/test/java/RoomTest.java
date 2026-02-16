import core.ApiRequestHelper;
import entities.auth.Auth;
import entities.auth.Token;
import entities.room.Room;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Booker Platform Admin")
@Feature("Room")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomTest extends BaseTest {
    /*

    Validar requisição GET /room - estrutura do payload
    Validar requisição GET /room - filtrar por datas
    Validar requisição GET /room - formato de data inválido
    Validar requisição GET /room/{id} - status code com ID válido
    Validar requisição GET /room/{id} - estrutura do payload
    Validar requisição GET /room/{id} - quarto inexistente
    Validar requisição GET /room/{id} - identificação não numérica
    Validar requisição POST /room - status code com dados válidos
    Validar requisição POST /room - estrutura do payload
    Validar requisição POST /room - preço em falta
    Validar requisição POST /room - sem token de autenticação
    Validar requisição POST /room - token inválido
    Validar requisição POST /room - preço negativo
    Validar requisição PUT /room/{id} - status code com dados válidos
    Validar requisição PUT /room/{id} - estrutura do payload
    Validar requisição PUT /room/{id} - sem token de autenticação
    Validar requisição PUT /room/{id} - quarto inexistente
    Validar requisição DELETE /room/{id} - status code com id válido
    Validar requisição DELETE /room/{id} - já deletado
    Validar requisição DELETE /room/{id} - sem token de autenticação
    Validar requisição DELETE /room/{id} - quarto inexistente
     */

    // Pré-condição
    Auth session = new Auth("admin", "password");
    Token sessionToken = new Token();
    ArrayList<Room> rooms = new ArrayList<>();

    @BeforeEach
    void setup(){
        sessionToken.setToken(session.getCookieToken(session));
        setBasePath("/room");

        rooms.clear();

        rooms.add(new Room(1,
                "101",
                "Single",
                true,
                "/images/room1.jpg",
                "Aenean porttitor mauris sit amet lacinia molestie. In posuere accumsan aliquet. Maecenas sit amet nisl massa. Interdum et malesuada fames ac ante.",
                new String[]{"TV", "WiFi", "Safe"},
                100));
        rooms.add(new Room(2,
                "102",
                "Double",
                true,
                "/images/room2.jpg",
                "Vestibulum sollicitudin, lectus ac mollis consequat, lorem orci ultrices tellus, eleifend euismod tortor dui egestas erat. Phasellus et ipsum nisl. ",
                new String[]{"TV", "Radio","Safe"},
                150));
        rooms.add(new Room(3,
                "103",
                "Suite",
                true,
                "/images/room3.jpg",
                "Etiam metus metus, fringilla ac sagittis id, consequat vel neque. Nunc commodo quis nisl nec posuere. Etiam at accumsan ex. ",
                new String[]{"Radio", "WiFi", "Safe"},
                225));
    }
    // ----------

    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;

    @Test
    @Order(1)
    @DisplayName("Validar requisição GET /room - estrutura do payload")
    void validarQuartoStatusCode(){
        response = request.get();

        List<Room> roomsReturned = response.extract().response().getBody().as(Room.class).getRooms();
        response.assertThat().statusCode(200);
        
        assertEquals(rooms.get(0).toString(), roomsReturned.get(0).toString());
        assertEquals(rooms.get(1).toString(), roomsReturned.get(1).toString());
        assertEquals(rooms.get(2).toString(), roomsReturned.get(2).toString());


    }
}
