package ru.geekbrains.domain;

import java.util.Map;

/**
 * Класс запроса
 */
public class HttpRequest {

    private String method;                  // Метод HTTP запроса
    private String path;                    // URL запроса
    private Map<String, String> header;     // Коллекция заголовков запроса
    private String body;                    // Тело запроса

    public HttpRequest() {
    }

    public HttpRequest(String method, String path, Map<String, String> header, String body) {
        this.method = method;
        this.path = path;
        this.header = header;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
