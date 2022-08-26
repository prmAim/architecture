package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private final SocketService socketService;

    private final FileService fileService;
    private final RequestParser requestParser;
    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketService socketService,
                          FileService fileService,
                          RequestParser requestParser,
                          ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.fileService = fileService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        // разбор запроса от клиента
        HttpRequest req = requestParser.parse(rawRequest);

      if (!fileService.exists(req.getUrl())) {
          HttpResponse resp = HttpResponse.createBuilder()
                  .withStatusCode(404)
                  .withStatusCodeName("NOT_FOUND")
                  .withBody("<h1>Указаный фаил не найден!</h1>")
                  .builder();
          resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
          // Отправка данных клиенту
          socketService.writeResponse(responseSerializer.serialize(resp));
          return;
      }

      if (fileService.isDirectory(req.getUrl())) {
          HttpResponse resp = HttpResponse.createBuilder()
                  .withStatusCode(500)
                  .withStatusCodeName("Internal Server Error")
                  .withBody("<h1>Не указан фаил!</h1>")
                  .builder();
          resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");

          // Отправка данных клиенту
          socketService.writeResponse(responseSerializer.serialize(resp));
      } else {
          HttpResponse resp = HttpResponse.createBuilder()
                  .withStatusCode(200)
                  .withStatusCodeName("OK")
                  .withBody(fileService.readFile(req.getUrl()))
                  .builder();
          resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");

          // Отправка данных клиенту
          socketService.writeResponse(responseSerializer.serialize(resp));
      }

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("LOG: Client disconnected!");
    }
}
