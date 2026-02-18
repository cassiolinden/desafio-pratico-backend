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
        return requestBuilder.makeRequest("GET", null, null, new HashMap<>(), null);
    }

    public ValidatableResponse get(Map<String, String> params){
        return requestBuilder.makeRequest("GET", null, null, params, null);
    }

    public ValidatableResponse post(Map<String, Object> body, Map<String, String> headers) {
        return requestBuilder.makeRequest("POST", body, headers, new HashMap<>(), null);
    }

    public ValidatableResponse post(Object body) {
        return requestBuilder.makeRequest("POST", body, null, new HashMap<>(), null);
    }

    public ValidatableResponse post(Object body, Map<String, String> cookie) {
        return requestBuilder.makeRequest("POST", body, null, new HashMap<>(), cookie);
    }

    public ValidatableResponse post() {
        return requestBuilder.makeRequest("POST", null, null, new HashMap<>(), null);
    }

    public ValidatableResponse put(Object body, Map<String, String> cookie) {
        return requestBuilder.makeRequest("PUT", body, null, new HashMap<>(), cookie);
    }

    public ValidatableResponse put(Object body) {
        return requestBuilder.makeRequest("PUT", body, null, new HashMap<>(), null);
    }

    public ValidatableResponse delete(Map<String, String> cookie) {
        return requestBuilder.makeRequest("DELETE", null, null, new HashMap<>(), cookie);
    }

    public ValidatableResponse delete() {
        return requestBuilder.makeRequest("DELETE", null, null, new HashMap<>(), null);
    }

    public void assertSchema(ValidatableResponse vr, String schemaFile) {
        vr.assertThat().body(matchesJsonSchemaInClasspath(schemaFile));
    }
}