package ru.geekbrains.service;

import java.net.Socket;

/**
 * Pattern Factory для сервиса SocketService
 */
public final class SocketServiceFactory {
  public static SocketService create(Socket socket) {
    return new SocketServiceImpl(socket);
  }
}
