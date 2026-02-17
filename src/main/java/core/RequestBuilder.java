package core;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilder {

    public RequestBuilder() {

    }

    private RequestSpecification buildRequest() {
        return RestAssured.given()
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
            case "GET" -> request.log().all().get().then().log().all();
            case "POST" -> request.log().all().post().then().log().all();
            case "PUT" -> request.log().all().put().then().log().all();
            case "PATCH" -> request.log().all().patch().then().log().all();
            default -> throw new IllegalArgumentException("Método HTTP não suportado: " + method);
        };
    }
}