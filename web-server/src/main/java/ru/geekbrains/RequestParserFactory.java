package ru.geekbrains;

/**
 * Pattern Factory для сервиса RequestParser (разбор строки запроса от клиента)
 */
public final class RequestParserFactory {

  public static RequestParser create(){
    return new RequestParserImpl();
  }
}
