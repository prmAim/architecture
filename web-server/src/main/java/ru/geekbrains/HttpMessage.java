package ru.geekbrains;

import java.util.HashMap;
import java.util.Map;

/**
 * Список сообщений
 */
public class HttpMessage {
  private final String CONTENT_TYPE = "Content-Type: text/html; charset=utf-8\n\n";
  private final Map<Integer, String> messenges;

  public HttpMessage() {
    this.messenges = new HashMap<>();
    messenges.put(200, "HTTP/1.1 200 OK\n" + CONTENT_TYPE);
    messenges.put(404, "HTTP/1.1 404 NOT_FOUND\n" + CONTENT_TYPE + "<h1>Файл не найден!</h1>\n");
    messenges.put(500, "HTTP/1.1 500 Internal Server Error\n" + CONTENT_TYPE);
  }

  public String getMessenges(int code) {
    return messenges.getOrDefault(code, "HTTP/1.1 500 Internal Server Error " + CONTENT_TYPE);
  }
}
