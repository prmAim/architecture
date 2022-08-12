package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

  /**
   * Парсиниг строки запроса
   */
  public HttpRequest parse(Deque<String> rawRequest){
    HttpRequest httpRequest = new HttpRequest();

    httpRequest.setBody(rawRequest.pollLast());
    String[] method = rawRequest.pollFirst().split(" ", 3);
    httpRequest.setMethod(method[0]);
    httpRequest.setPath(method[1]);

    Map<String, String> map = new HashMap<>();
    while(rawRequest.peekFirst() != null){
      String[] s = rawRequest.poll().split(":", 2);
      map.put(s[0], s[1]);
    }
    httpRequest.setHeader(map);

    return httpRequest;
  }
}
