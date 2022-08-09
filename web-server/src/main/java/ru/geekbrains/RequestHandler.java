package ru.geekbrains;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;

public class RequestHandler implements Runnable {
  private final Socket socket;
  private final String folder;
  private final HttpMessage httpMessage;

  public RequestHandler(Socket socket, String folder) {
    this.socket = socket;
    this.folder = folder;
    this.httpMessage = new HttpMessage();
  }

  @Override
  public void run() {
    try {
      InOutStreams inOutStream = new InOutStreamsImpl(socket);
      while (!inOutStream.getStatus()) ;

      PathFiles pathFiles = new PathFiles(folder, inOutStream.inputStreamReadLine());

      if (pathFiles.isExistsFile()) {
        inOutStream.sendResponse(httpMessage.getMessenges(404));
        return;
      }

      inOutStream.sendResponse(httpMessage.getMessenges(200));

      Files.newBufferedReader(pathFiles.getPathFile()).transferTo(inOutStream.getOutput());
    } catch (
            IOException e) {
      e.printStackTrace();
    } finally {
      System.out.println("LOG: Client disconnected!");
      try {
        socket.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
