package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {

  /**
   * Парсиниг строки запроса
   */
  HttpRequest parse(Deque<String> rawRequest);

}
