package ru.geekbrains;

import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {
    private final SocketService socketService;
    private final FileService fileService;

  public RequestHandler(SocketService socketService, FileService fileService) {
        this.socketService = socketService;
        this.fileService = fileService;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();

        // Получаем первую строку
        String firstLine = rawRequest.pollFirst();
        String[] parts = firstLine.split(" ");

        if (!fileService.exists(parts[1])){
          String rawResponce = "HTTP/1.1 404 NOT_FOUND\n"
                  + "Content-Type: text/html; charset=utf-8\n"
                  + "\n"
                  + "<h1>Файл не найден!</h1>";
          // Отправка данных клиенту
          socketService.writeResponse(rawResponce);
        }

        if (fileService.isDirectory(parts[1])){
          String rawResponce = "HTTP/1.1 500 Internal Server Error\n"
                  + "Content-Type: text/html; charset=utf-8\n"
                  + "\n"
                  + "<h1>Не указан фаил!</h1>";
          // Отправка данных клиенту
          socketService.writeResponse(rawResponce);
        } else {
          String rawResponce = "HTTP/1.1 200 OK\n"
                  + "Content-Type: text/html; charset=utf-8\n"
                  + "\n"
                  + fileService.readFile(parts[1]);     // данные из файла

          // Отправка данных клиенту
          socketService.writeResponse(rawResponce);
        }

      // Закрываем соединение. Что бы избежать утечки ресурсов.
      try {
        socketService.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
}
