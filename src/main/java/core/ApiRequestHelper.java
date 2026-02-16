package core;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiRequestHelper {
    private final RequestBuilder requestBuilder;
    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    public ApiRequestHelper() {
        this.requestBuilder = new RequestBuilder();
    }

    public Response get(String endpoint, Map<String, String> headers) {
        return requestBuilder.makeRequest("GET", endpoint, null, headers);
    }

    public Response post(String endpoint, Map<String, Object> body, Map<String, String> headers) {
        return requestBuilder.makeRequest("POST", endpoint, body, headers);
    }

    public Response post(String endpoint, Map<String, Object> body) {
        return requestBuilder.makeRequest("POST", endpoint, body, null);
    }

    public Response post(String endpoint) {
        return requestBuilder.makeRequest("POST", endpoint, null, null);
    }

    public Response put(String endpoint, Map<String, Object> body, Map<String, String> headers) {
        return requestBuilder.makeRequest("PUT", endpoint, body, headers);
    }

    public Response patch(String endpoint, Map<String, Object> body, Map<String, String> headers) {
        return requestBuilder.makeRequest("PATCH", endpoint, body, headers);
    }
}