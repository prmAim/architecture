package ru.geekbrains.domain;

import java.util.Map;

/**
 * Класс ответа
 */
public class HttpResponce {

    private int statusCode;                     // статус ответа
    private Map<String, String> headers;        // заголовки ответа
    private String body;                        // тело ответа

    public HttpResponce() {
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
}
