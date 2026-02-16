package core;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilder {

    public RequestBuilder() {

    }

    private RequestSpecification buildRequest() {
        return RestAssured.given()
                .header("Content-Type", "application/json");
    }

    public Response makeRequest(String method, String endpoint, Map<String, Object> body, Map<String, String> headers) {
        RequestSpecification request = buildRequest();

        if (body != null) {
            request.body(body);
        }

        if (headers != null) {
            request.headers(headers);
        }

        return switch (method.toUpperCase()) {
            case "GET" -> request.log().all().get(endpoint).then().log().all().extract().response();
            case "POST" -> request.log().all().post(endpoint).then().log().all().extract().response();
            case "PUT" -> request.log().all().put(endpoint).then().log().all().extract().response();
            case "PATCH" -> request.log().all().patch(endpoint).then().log().all().extract().response();
            default -> throw new IllegalArgumentException("Método HTTP não suportado: " + method);
        };
    }
}