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

    private HttpResponse() {
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

    /**
     * Фабричный метод для создания Pattern Builder класса HttpResponse
     */
    public static Builder createBuilder(){
        return new Builder();
    }

    /**
     * Внутрений класс для создания Pattern Builder
     */
    public static class Builder{

        private final HttpResponse httpResponse;

        public Builder(){
            this.httpResponse = new HttpResponse();
        }
        public Builder withStatusCode(int statusCode){
            this.httpResponse.statusCode = statusCode;
            return this;
        }
        public Builder withStatusCodeName(String statusCodeName){
            this.httpResponse.statusCodeName = statusCodeName;
            return this;
        }
        public Builder withHeaders(Map<String, String> headers){
            this.httpResponse.headers = headers;
            return this;
        }
        public Builder withBody(String body){
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse builder(){
            return this.httpResponse;
        }
    }
}
