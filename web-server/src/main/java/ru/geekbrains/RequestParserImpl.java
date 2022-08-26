package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

class RequestParserImpl implements RequestParser{

  /**
   * Парсиниг строки запроса
   */
  public HttpRequest parse(Deque<String> rawRequest) {
      String[] method = rawRequest.pollFirst().split(" ", 3);

      HttpRequest httpRequest = HttpRequest.createBuilder()
              .withBody(rawRequest.pollLast())
              .withMethod(method[0])
              .withUrl(method[1])
              .builder();

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
