package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс ответа
 */
public class HttpResponse {

    private int statusCode;                                 // статус ответа

    private String statusCodeName;                          // заголовки ответа

    private Map<String, String> headers = new HashMap<>();  // заголовки ответа

    private String body;                                    // тело ответа

    public HttpResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatusCodeName() {
        return statusCodeName;
    }

    public void setStatusCodeName(String statusCodeName) {
        this.statusCodeName = statusCodeName;
    }
}
