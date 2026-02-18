package core;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;

import java.util.Map;

public class RequestBuilder {

    public RequestBuilder() {

    }

    private RequestSpecification buildRequest() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json");
    }

    public ValidatableResponse makeRequest(String method, Object body, Map<String, String> headers, Map<String, String> params, Map<String, String> cookie) {
        RequestSpecification request = buildRequest();

        if (body != null) {
            request.body(body);
        }

        if (headers != null) {
            request.headers(headers);
        }

        if (!params.isEmpty()){
            request.params(params);
        }

        if(cookie != null){
            request.cookies(cookie);
        }

        return switch (method.toUpperCase()) {
            case "GET" -> request.get().then();
            case "POST" -> request.post().then();
            case "PUT" -> request.put().then();
            case "DELETE" -> request.delete().then();
            default -> throw new IllegalArgumentException("Método HTTP não suportado: " + method);
        };
    }
}