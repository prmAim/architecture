package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

  private static String WWW = "web-server/www";
  private static final int PORT = 8080;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Server started!");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("New client connected!");

        new Thread(new RequestHandler(socket, WWW)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
