package ru.geekbrains;

/**
 * Pattern Factory для сервиса ResponseSerializer (подготовка ответа клиенту)
 */
public final class ResponseSerializerFactory {
  public static ResponseSerializer create(){
    return new ResponseSerializerImpl();
  }
}
