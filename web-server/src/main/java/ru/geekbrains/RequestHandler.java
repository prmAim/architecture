package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponce;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class RequestHandler implements Runnable {
  private final SocketService socketService;
  private final FileService fileService;

  public RequestHandler(SocketService socketService, FileService fileService) {
    this.socketService = socketService;
    this.fileService = fileService;
  }

  @Override
  public void run() {
    // разбор запроса от клиента
    HttpRequest httpRequest = new RequestParser().parse(socketService.readRequest());

    if (!fileService.exists(httpRequest.getPath())) {
      String rawResponce = "HTTP/1.1 404 NOT_FOUND\n"
              + "Content-Type: text/html; charset=utf-8\n"
              + "\n"
              + "<h1>Файл не найден!</h1>";
      // Отправка данных клиенту
      socketService.writeResponse(new ResponceSerializer()
              .serialaze(new HttpResponce(404
                      , new HashSet<>(Arrays.asList("Content-Type"))
                      , "<h1>Файл не найден!</h1>")));
    }

    if (fileService.isDirectory(httpRequest.getPath())) {
      // Отправка данных клиенту
      socketService.writeResponse(new ResponceSerializer()
              .serialaze(new HttpResponce(500
                      , new HashSet<>(Arrays.asList("Content-Type"))
                      , "<h1>Не указан фаил!</h1>")));
    } else {
      // Отправка данных клиенту
      socketService.writeResponse(new ResponceSerializer()
              .serialaze(new HttpResponce(200
                      , new HashSet<>(Arrays.asList("Content-Type"))
                      , fileService.readFile(httpRequest.getPath()))));
    }

    // Закрываем соединение. Что бы избежать утечки ресурсов.
    try {
      socketService.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
