package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс запроса
 */
public class HttpRequest {

    private String method;                                  // Метод HTTP запроса

    private String url;                                     // URL запроса

    private Map<String, String> headers = new HashMap<>();  // Коллекция заголовков запроса

    private String body;                                    // Тело запроса

  private HttpRequest() {
  }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

  /**
   * Фабричный метод для создания Pattern Builder класса HttpRequest
   */
  public static Builder createBuilder() {
    return new Builder();
  }

  /**
   * Внутрений класс для создания Pattern Builder
   */
  public static class Builder {
    private final HttpRequest httpRequest;

    public Builder() {
      this.httpRequest = new HttpRequest();
    }

    public Builder withMethod(String method) {
      this.httpRequest.method = method;
      return this;
    }

    public Builder withUrl(String url) {
      this.httpRequest.url = url;
      return this;
    }

    public Builder withBody(String body) {
      this.httpRequest.body = body;
      return this;
    }

    public Builder withBody(Map<String, String> headers) {
      this.httpRequest.headers = headers;
      return this;
    }

    public HttpRequest builder() {
      return httpRequest;
    }
  }
}
