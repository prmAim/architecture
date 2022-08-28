package ru.geekbrains.service;

import java.io.IOException;
import java.util.Deque;

public interface SocketService {

  /**
   * Чтение данных запроса от клиента
   */
  public Deque<String> readRequest();

  /**
   * Отправка ответа клиенту
   */
  void writeResponse(String rawResponse);

  /**
   * Закрытие соединения Socket
   */
  void close() throws IOException;
}
