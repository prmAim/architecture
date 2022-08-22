package ru.geekbrains;

import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

  private static String WWW = "web-server/www";
  private static final int PORT = 8080;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("LOG: Server started!");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("LOG: New client connected!");

        // Уменьшение связаности объектов!!!
        new Thread(new RequestHandler(new SocketService(socket), new FileService(WWW))).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
