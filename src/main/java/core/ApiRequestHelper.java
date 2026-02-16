package core;

import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

public class ApiRequestHelper {
    private final RequestBuilder requestBuilder;

    public ApiRequestHelper() {
        this.requestBuilder = new RequestBuilder();
    }

    public ValidatableResponse get() {
        return requestBuilder.makeRequest("GET", null, null, new HashMap<>());
    }

    public ValidatableResponse post(Map<String, Object> body, Map<String, String> headers) {
        return requestBuilder.makeRequest("POST", body, headers, new HashMap<>());
    }

    public ValidatableResponse post(Object body) {
        return requestBuilder.makeRequest("POST", body, null, new HashMap<>());
    }

    public ValidatableResponse post() {
        return requestBuilder.makeRequest("POST", null, null, new HashMap<>());
    }
}