package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public interface ResponseSerializer {
  /**
   * Объединение в ответ
   */
  String serialize(HttpResponse response);
}
