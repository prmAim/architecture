package ru.geekbrains;

import org.junit.Assert;
import org.junit.Test;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Класс для тестирования класса ResponseSerializer
 */
public class ResponseSerializerTest {
  private static final HttpResponse resp = new HttpResponse();

  private static final ResponseSerializerImpl responeSerializer = new ResponseSerializerImpl();

  private static String standardTest = "HTTP/1.1 404 NOT_FOUND\n" +
                                 "Content-Type: text/html; charset=utf-8\n" +
                                 '\n' +
                                 "<h1>Test body</h1>";

  @Test
  public void testSerialize(){
    resp.setStatusCode(404);
    resp.setStatusCodeName("NOT_FOUND");
    resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
    resp.setBody("<h1>Test body</h1>");

    Assert.assertEquals(standardTest, responeSerializer.serialize(resp));
  }
}
