package core;

import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiRequestHelper {
    private final RequestBuilder requestBuilder;

    public ApiRequestHelper() {
        this.requestBuilder = new RequestBuilder();
    }

    public ValidatableResponse get() {
        return requestBuilder.makeRequest("GET", null, null, new HashMap<>());
    }

    public ValidatableResponse get(Map<String, String> params){
        return requestBuilder.makeRequest("GET", null, null, params);
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

    public void assertSchema(ValidatableResponse vr, String schemaFile) {
        vr.assertThat().body(matchesJsonSchemaInClasspath(schemaFile));
    }
}