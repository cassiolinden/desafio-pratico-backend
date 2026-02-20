import core.ApiRequestHelper;
import entities.auth.Auth;
import entities.booking.BookingDates;
import entities.room.Room;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Booker Platform Admin")
@Feature("Room")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoomTest extends BaseTest {
    // Pré-condição
    ArrayList<Room> rooms = new ArrayList<>();
    HashMap<String, String> cookies = new HashMap<>();
    BookingDates bookingDates;
    Auth session = new Auth("admin", "password");

    @BeforeEach
    void setup(){
        setBasePath("/room");

        rooms.clear();
        cookies.clear();

        // Quartos padrões do sistema
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

        // Novo quarto
        rooms.add(new Room(4,
                "Premium Room",
                "Single",
                true,
                "/images/room1.jpg",
                "Descrição do Premium Room",
                new String[]{"Radio", "WiFi", "TV"},
                250));
    }
    // ----------

    ApiRequestHelper request = new ApiRequestHelper();
    static ValidatableResponse response;

    @Test
    @Order(1)
    @DisplayName("Validar requisição GET /room - estrutura do payload")
    void validarQuartoStatusCode(){
        response = request.get();

        List<Room> roomsReturned = response.extract().as(Room.class).getRooms();

        response.assertThat().statusCode(200);
        
        assertEquals(rooms.get(0).toString(), roomsReturned.get(0).toString());
        assertEquals(rooms.get(1).toString(), roomsReturned.get(1).toString());
        assertEquals(rooms.get(2).toString(), roomsReturned.get(2).toString());

        request.assertSchema(response, "rooms-schema.json");
    }

    @Test
    @Order(2)
    @DisplayName("Validar requisição GET /room - filtrar por datas")
    void validarFiltroDeDatas(){
        bookingDates = new BookingDates(
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 12));
        response = request.get(bookingDates.getBookingDates());

        response.assertThat().statusCode(200);
        request.assertSchema(response, "rooms-schema.json");
    }

    @Test
    @Order(3)
    //@Disabled // está retornando 500 após timeout
    @DisplayName("Validar requisição GET /room - formato de data inválido")
    void validarDataFormatoInvalido(){
        bookingDates = new BookingDates(
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 12));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        HashMap<String, String> dates = new HashMap<>();
        dates.put("checkin", bookingDates.getCheckin().format(formatter));
        dates.put("checkout", bookingDates.getCheckout().format(formatter));
        response = request.get(dates);

        response.assertThat().statusCode(500);
    }

    @Test
    @Order(4)
    @DisplayName("Validar requisição GET /room/{id} - status code com ID válido")
    void validarRetornoComId(){
        setBasePath("/room/1");
        response = request.get();

        response.assertThat().statusCode(200);

        assertEquals(rooms.get(0).toString(), response.extract().as(Room.class).toString());

        request.assertSchema(response, "room-schema.json");
    }

    @Test
    @Order(5)
    //@Disabled // está retornando 500 após timeout
    @DisplayName("Validar requisição GET /room/{id} - quarto inexistente")
    void validarRetornoComIdInvalido(){
        setBasePath("/room/99999");
        response = request.get();

        response.assertThat().statusCode(500);
    }

    @Test
    @Order(6)
    @DisplayName("Validar requisição GET /room/{id} - identificação não numérica")
    void validarRetornoComIdNaoNumerico(){
        setBasePath("/room/abc");
        response = request.get();

        response.assertThat().statusCode(404);
    }

    @Test
    @Order(7)
    @DisplayName("Validar requisição POST /room - status code e payload com dados válidos")
    void validarPostValido(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room");
        Room room = new Room(4,
                "Premium Room",
                "Suite",
                false,
                "/images/room1.jpg",
                "Descrição do Premium Room",
                new String[]{"Radio", "WiFi", "TV"},
                350);
        response = request.post(room, cookies);

        response.assertThat().statusCode(200);
        assertEquals(true, response.extract().path("success"));
    }

    @Test
    @Order(8)
    @DisplayName("Validar requisição POST /room - tipo de quarto fora do esperado")
    void validarPostTipoDiferenteDoEsperado(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room");
        Room room = new Room(4,
                "Premium Room",
                "Deluxe",
                false,
                "/images/room1.jpg",
                "Descrição do Premium Room",
                new String[]{"Radio", "WiFi", "TV"},
                350);
        response = request.post(room, cookies);

        response.assertThat().statusCode(400);
        String[] errors = {"Type can only contain the room options Single, Double, Twin, Family or Suite"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(9)
    @DisplayName("Validar requisição POST /room - preço em falta")
    void validarPostSemPreco(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room");
        rooms.get(3).setRoomPrice(0);

        response = request.post(rooms.get(3), cookies);

        response.assertThat().statusCode(400);

        String[] errors = {"must be greater than or equal to 1"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(10)
    @DisplayName("Validar requisição POST /room - sem token de autenticação")
    void validarPostSemToken(){
        response = request.post(rooms.get(0));

        response.assertThat().statusCode(401);
        String[] errors = {"Authentication required"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(11)
    @DisplayName("Validar requisição POST /room - token inválido")
    void validarPostComTokenInvalido(){
        cookies.put("token", "B8hKXddVwPf2b2jT");
        Room room = new Room(4,
                "Premium Room",
                "Single",
                true,
                "/images/room1.jpg",
                "Descrição do Premium Room",
                new String[]{"Radio", "WiFi", "TV"},
                250);
        response = request.post(room, cookies);

        response.assertThat().statusCode(500);
        String[] errors = {"An unexpected error occurred"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(12)
    @DisplayName("Validar requisição POST /room - preço negativo")
    void validarPostComPrecoNegativo(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room");
        rooms.get(3).setRoomPrice(-150);

        response = request.post(rooms.get(3), cookies);

        response.assertThat().statusCode(400);

        String[] errors = {"must be greater than or equal to 1"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(13)
    @DisplayName("Validar requisição PUT /room/{id} - status code e payload com dados válidos")
    void validarPutValido(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/3");

        response = request.put(rooms.get(2).setRoomPrice(100), cookies);
        response.assertThat().statusCode(200);
        assertEquals(true, response.extract().path("success"));
    }

    @Test
    @Order(14)
    @DisplayName("Validar requisição PUT /room/{id} - sem token de autenticação")
    void validarPutSemToken(){
        setBasePath("/room/3");
        response = request.put(rooms.get(2).setRoomPrice(125));
        response.assertThat().statusCode(401);
        String[] errors = {"Authentication required"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(15)
    @DisplayName("Validar requisição PUT /room/{id} - quarto inexistente")
    void validarPutQuartoInexistente(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/99999");
        response = request.put(rooms.get(2).setRoomPrice(125), cookies);
        response.assertThat().statusCode(500);
        String[] errors = {"An unexpected error occurred"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(16)
    @DisplayName("Validar requisição DELETE /room/{id} - status code com id válido")
    void validarDeleteIdValido(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/4");
        response = request.delete(cookies);

        response.assertThat().statusCode(200);
        assertEquals(true, response.extract().path("success"));
    }

    @Test
    @Order(17)
    @DisplayName("Validar requisição DELETE /room/{id} - já deletado")
    void validarDeleteIdJaUtilizado(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/4");
        response = request.delete(cookies);

        response.assertThat().statusCode(500);
        String[] errors = {"An unexpected error occurred"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(18)
    @DisplayName("Validar requisição DELETE /room/{id} - sem token de autenticação")
    void validarDeleteIdSemToken(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/4");
        response = request.delete();

        response.assertThat().statusCode(401);
    }

    @Test
    @Order(19)
    @DisplayName("Validar requisição DELETE /room/{id} - quarto inexistente")
    void validarDeleteIdInexistente(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/881728");
        response = request.delete(cookies);

        response.assertThat().statusCode(500);
        String[] errors = {"An unexpected error occurred"};
        assertEquals(errors[0], response.extract().path("errors[0]"));
    }

    @Test
    @Order(20)
    @DisplayName("Validar requisição DELETE /room/{id} - quarto com reservas vinculadas")
    void validarDeleteQuartoComReserva(){
        cookies.put("token", session.getToken(session));
        setBasePath("/room/1");
        response = request.delete(cookies);
    }
}
