package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

class RequestParserImpl implements RequestParser{

  /**
   * Парсиниг строки запроса
   */
  public HttpRequest parse(Deque<String> rawRequest){
    HttpRequest httpRequest = new HttpRequest();

    httpRequest.setBody(rawRequest.pollLast());
    String[] method = rawRequest.pollFirst().split(" ", 3);
    httpRequest.setMethod(method[0]);
    httpRequest.setUrl(method[1]);

        while (!rawRequest.isEmpty()) {
            String line = rawRequest.pollFirst();
            if (line.isBlank()) {
                break;
            }
            String[] header = line.split(": ");
            httpRequest.getHeaders().put(header[0], header[1]);
        }
        StringBuilder sb = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            sb.append(rawRequest.pollFirst());
        }
        httpRequest.setBody(sb.toString());
        return httpRequest;
    }
}
