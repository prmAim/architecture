package ru.geekbrains;

import org.junit.Assert;
import org.junit.Test;
import ru.geekbrains.domain.HttpResponse;

/**
 * Класс для тестирования класса ResponseSerializer
 */
public class ResponseSerializerTest {
  private static final ResponseSerializerImpl responeSerializer = new ResponseSerializerImpl();
  private static String standardTest = "HTTP/1.1 404 NOT_FOUND\n" +
                                 "Content-Type: text/html; charset=utf-8\n" +
                                 '\n' +
                                 "<h1>Test body</h1>";
  @Test
  public void testSerialize(){
    HttpResponse resp = HttpResponse.createBuilder()
            .withStatusCode(404)
            .withStatusCodeName("NOT_FOUND")
            .withBody("<h1>Test body</h1>")
            .builder();
    resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");

    Assert.assertEquals(standardTest, responeSerializer.serialize(resp));
  }
}
