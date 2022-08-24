package ru.geekbrains;

import ru.geekbrains.config.*;
import ru.geekbrains.service.FileServiceFactory;
import ru.geekbrains.service.SocketServiceFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
  public static void main(String[] args) {
    // Pattern Factory => связаность компонентов низкая
    ServerConfig config = ServerConfigFactory.create(args);

    try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
      System.out.println("LOG: Server started! Port: " + config.getPort());

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("LOG: New client connected!");

        // Уменьшение связаности объектов!!!
        new Thread(new RequestHandler(
                SocketServiceFactory.create(socket),
                FileServiceFactory.create(config.getWww()),
                RequestParserFactory.create(),
                ResponseSerializerFactory.create()
        )).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
