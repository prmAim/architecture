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
            HttpResponse resp = new HttpResponse();
            resp.setStatusCode(404);
            resp.setStatusCodeName("NOT_FOUND");
            resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
            resp.setBody("<h1>Указаный фаил не найден!</h1>");
            // Отправка данных клиенту
            socketService.writeResponse(responseSerializer.serialize(resp));
            return;
        }

        if (fileService.isDirectory(req.getUrl())) {
            HttpResponse resp = new HttpResponse();
            resp.setStatusCode(500);
            resp.setStatusCodeName("Internal Server Error");
            resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
            resp.setBody("<h1>Не указан фаил!</h1>");
            // Отправка данных клиенту
            socketService.writeResponse(responseSerializer.serialize(resp));
        } else {
            HttpResponse resp = new HttpResponse();
            resp.setStatusCode(200);
            resp.setStatusCodeName("OK");
            resp.getHeaders().put("Content-Type", "text/html; charset=utf-8");
            resp.setBody(fileService.readFile(req.getUrl()));
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
