package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Класс ответа
 */
public class HttpResponce {

    private int statusCode;                     // статус ответа
    private Map<String, String> headers;        // заголовки ответа
    private String body;                        // тело ответа
    private Set<String> headersSet;             // заголовки ответа

    private Map<Integer, String> statusHttp;

    public HttpResponce() {
    }

    public HttpResponce(int statusCode, Set<String> headersSet, String body) {
        this.statusCode = statusCode;
        this.headersSet = headersSet;
        this.body = body;

        this.statusHttp = new HashMap<>();
        statusHttp.put(200, "HTTP/1.1 200 OK\n");
        statusHttp.put(404, "HTTP/1.1 404 NOT_FOUND\n");
        statusHttp.put(500, "HTTP/1.1 500 Internal Server Error\n");

        this.headers = new HashMap<>();
        headers.put("Content-Type", "Content-Type: text/html; charset=utf-8\n");
    }

    public String getStatusHttp(){
        return statusHttp.getOrDefault(statusCode, statusHttp.get(500));
    }
    public String getAllHeaders(){
        StringBuilder sb = new StringBuilder();
        headers.forEach((key, value) -> {
            for (String s : headersSet) {
                if (key == s){
                    sb.append(headers.get(key));
                }
            }
        });
        return sb.toString();
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

    @Override
    public String toString() {
        return "HttpResponce{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
