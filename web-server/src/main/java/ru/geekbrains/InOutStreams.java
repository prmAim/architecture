package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;

public interface InOutStreams {
  /**
   * Получить статус потока
   * @return
   */
  boolean getStatus() throws IOException;

  /**
   * Получить данные от клиента
   */
  String inputStreamReadLine() throws IOException;

  /**
   * Отправка ответа от сервера
   */
  void sendResponse(String httpMessage) throws IOException;

  PrintWriter getOutput()  throws IOException;
}
